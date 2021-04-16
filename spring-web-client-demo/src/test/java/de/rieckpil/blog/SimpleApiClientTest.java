package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleApiClientTest {

  private MockWebServer mockWebServer;
  private SimpleApiClient cut; // Class Under Test

  @BeforeEach
  void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    this.cut = new SimpleApiClient(WebClient
      .builder()
      .baseUrl(mockWebServer.url("/").toString()).build(), new ObjectMapper());
  }

  @Test
  void testGetUserById() throws InterruptedException {
    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody("{\"id\": 1, \"name\":\"write good tests\"}");

    mockWebServer.enqueue(mockResponse);

    JsonNode result = cut.getTodoFromAPI();

    assertEquals(1, result.get("id").asInt());
    assertEquals("write good tests", result.get("name").asText());

    RecordedRequest request = mockWebServer.takeRequest();
    assertEquals("/todos/1", request.getPath());
  }
}
