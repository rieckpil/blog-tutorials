package de.rieckpil.blog.mockwebserver;

import de.rieckpil.blog.client.JavaHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockWebServerTest {

  private JavaHttpClient cut;
  private MockWebServer mockWebServer;

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
      .setBody(new String(MockWebServerTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json")
        .readAllBytes()));

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
      .setBody(new String(MockWebServerTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json")
        .readAllBytes()))
      .setBodyDelay(2, TimeUnit.SECONDS);

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }

  @Test
  void shouldReturnRandomQuoteOn200ResponseVerification() throws IOException, InterruptedException {

    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(new String(MockWebServerTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json")
        .readAllBytes()));

    mockWebServer.enqueue(mockResponse);

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");

    RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertEquals("/mock/qod", recordedRequest.getPath());
    assertEquals("GET", recordedRequest.getMethod());
    assertEquals("application/json", recordedRequest.getHeader("Accept"));
  }
}
