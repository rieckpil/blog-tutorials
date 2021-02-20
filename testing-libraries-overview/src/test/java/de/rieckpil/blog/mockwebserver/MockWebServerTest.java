package de.rieckpil.blog.mockwebserver;

import de.rieckpil.blog.client.JavaHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockWebServerTest {

  private JavaHttpClient cut;
  private MockWebServer mockWebServer;

  private static String DEFAULT_RESPONSE;

  static {
    try {
      DEFAULT_RESPONSE = new String(MockWebServerTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json")
        .readAllBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeEach
  void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    this.cut = new JavaHttpClient(mockWebServer.url("/mock").toString());
  }

  @AfterEach
  void shutdown() throws IOException {
    this.mockWebServer.shutdown();
  }

  @Test
  void shouldReturnRandomQuoteOn200Response() throws IOException {

    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(DEFAULT_RESPONSE);

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }

  @Test
  void shouldReturnDefaultQuoteOnRequestFailure() {
    MockResponse mockResponse = new MockResponse()
      .addHeader("X-Error-Reason", "CloudProviderOutOfDiskSpace")
      .setResponseCode(500);

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Lorem ipsum dolor sit amet.");
  }

  @Test
  void shouldReturnQuoteOnSlowResponse() throws IOException {
    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(DEFAULT_RESPONSE)
      .setBodyDelay(2, TimeUnit.SECONDS);

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }

  @Test
  void shouldReturnRandomQuoteOn200ResponseVerification() throws IOException, InterruptedException {

    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(DEFAULT_RESPONSE);

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");

    RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertEquals("/mock/qod", recordedRequest.getPath());
    assertEquals("GET", recordedRequest.getMethod());
    assertEquals("application/json", recordedRequest.getHeader("Accept"));
  }

  @Test
  void shouldMultipleResponseVerification() throws IOException, InterruptedException {

    MockResponse firstResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(DEFAULT_RESPONSE);

    MockResponse secondResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(new String(MockWebServerTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json")
        .readAllBytes()));

    mockWebServer.enqueue(firstResponse);
    mockWebServer.enqueue(secondResponse);

    String randomQuote = this.cut.getRandomQuote();

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(mockWebServer.url("/mock") + "/foo"))
      .header("X-Custom-Header", "Duke42")
      .DELETE()
      .build();

    HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");

    RecordedRequest recordedRequestOne = mockWebServer.takeRequest();
    assertEquals("/mock/qod", recordedRequestOne.getPath());
    assertEquals("GET", recordedRequestOne.getMethod());
    assertEquals("application/json", recordedRequestOne.getHeader("Accept"));

    RecordedRequest recordedRequestTwo = mockWebServer.takeRequest();
    assertEquals("/mock/foo", recordedRequestTwo.getPath());
    assertEquals("DELETE", recordedRequestTwo.getMethod());
    assertEquals("Duke42", recordedRequestTwo.getHeader("X-Custom-Header"));
  }

  @Test
  void dispatcherExample() {

    final Dispatcher dispatcher = new Dispatcher() {

      @Override
      public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

        switch (request.getPath()) {
          case "/mock/qod":
            return new MockResponse()
              .addHeader("Content-Type", "application/json; charset=utf-8")
              .setBody(DEFAULT_RESPONSE)
              .setResponseCode(200);
          case "/mock/foo":
            return new MockResponse().setResponseCode(500).setBody("SERVER DOWN");
        }
        return new MockResponse().setResponseCode(404);
      }
    };

    mockWebServer.setDispatcher(dispatcher);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }
}
