package de.rieckpil.blog.deepstubs;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DontHurtFairyTest {

  private MockWebServer mockWebServer;
  private InspirationalQuotesClient cut;

  @BeforeEach
  public void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    this.cut = new InspirationalQuotesClient(WebClient
      .builder()
      .baseUrl(mockWebServer.url("/").toString())
      .build());
  }

  @Test
  void shouldReturnDefaultQuoteOnRemoteSystemFailure() throws InterruptedException {
    MockResponse mockResponse = new MockResponse()
      .setResponseCode(500);

    mockWebServer.enqueue(mockResponse);

    String result = cut.fetchRandomQuote();

    assertEquals("Every time a mock returns a mock, a fairy dies.", result);

    RecordedRequest request = mockWebServer.takeRequest();
    assertEquals("/api/quotes", request.getPath());
  }
}
