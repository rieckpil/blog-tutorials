package de.rieckpil.blog;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

class ApacheHttpClientTest {

  private MockWebServer mockWebServer;
  private ApacheHttpClient cut;

  private static String VALID_RESPONSE;

  static {
    try {
      VALID_RESPONSE = new String(requireNonNull(ApacheHttpClientTest.class
        .getClassLoader()
        .getResourceAsStream("stubs/random-quote-success.json"))
        .readAllBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @BeforeEach
  void init() {
    this.mockWebServer = new MockWebServer();
    this.cut = new ApacheHttpClient(mockWebServer.url("/").toString());
  }

  @Test
  void shouldReturnDefaultQuoteOnFailure() {
    mockWebServer.enqueue(new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setResponseCode(500));

    String result = cut.getRandomQuote();

    assertEquals("Lorem ipsum dolor sit amet.", result);
  }

  @Test
  void shouldReturnRandomQuoteOnSuccessfulResponse() {
    mockWebServer.enqueue(new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody(VALID_RESPONSE)
      .setResponseCode(200));

    String result = cut.getRandomQuote();

    assertEquals("Vision without action is daydream. Action without vision is nightmare..", result);
  }
}
