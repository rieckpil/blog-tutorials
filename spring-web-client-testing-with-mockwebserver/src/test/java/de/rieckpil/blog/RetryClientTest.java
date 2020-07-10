package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetryClientTest {

  private MockWebServer mockWebServer;
  private RetryClient retryClient;

  @BeforeEach
  public void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    this.retryClient = new RetryClient(WebClient.builder(), mockWebServer.url("/").toString(), new ObjectMapper());
  }

  @Test
  public void testRetry() {
    MockResponse failureResponse = new MockResponse()
      .setResponseCode(500);

    mockWebServer.enqueue(failureResponse);
    mockWebServer.enqueue(failureResponse);

    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody("{\"id\": 1, \"name\":\"duke\"}")
      .throttleBody(16, 5000, TimeUnit.MILLISECONDS);

    mockWebServer.enqueue(mockResponse);

    JsonNode result = retryClient.getData();

    System.out.println(result);
    assertEquals("fallback", result.get("message").asText());
  }

}
