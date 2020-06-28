package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsersClientTest {

  private MockWebServer mockWebServer;
  private UsersClient usersClient;

  @BeforeEach
  public void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    this.usersClient = new UsersClient(WebClient.builder(), mockWebServer.url("/").toString());
  }

  @Test
  public void testGetUserById() throws InterruptedException {
    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody("{\"id\": 1, \"name\":\"duke\"}")
      .throttleBody(16, 5, TimeUnit.SECONDS);

    mockWebServer.enqueue(mockResponse);

    JsonNode result = usersClient.getUserById(1L);

    assertEquals(1, result.get("id").asInt());
    assertEquals("duke", result.get("name").asText());

    RecordedRequest request = mockWebServer.takeRequest();
    assertEquals("/users/1", request.getPath());
  }

  @Test
  public void testCreatingUsers() {
    MockResponse mockResponse = new MockResponse()
      .addHeader("Content-Type", "application/json; charset=utf-8")
      .setBody("{\"id\": 1, \"name\":\"duke\"}")
      .throttleBody(16, 5, TimeUnit.SECONDS)
      .setResponseCode(201);

    mockWebServer.enqueue(mockResponse);

    JsonNode result = usersClient.createNewUser(new ObjectMapper().createObjectNode().put("name", "duke"));

    assertEquals(1, result.get("id").asInt());
    assertEquals("duke", result.get("name").asText());
  }

  @Test
  public void testCreatingUsersWithNon201ResponseCode() {
    MockResponse mockResponse = new MockResponse()
      .setResponseCode(204);

    mockWebServer.enqueue(mockResponse);

    assertThrows(RuntimeException.class, () ->
      usersClient.createNewUser(new ObjectMapper().createObjectNode().put("name", "duke")));
  }

  @Test
  public void testMultipleResponseCodes() {

    final Dispatcher dispatcher = new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest request) {
        switch (request.getPath()) {
          case "/users/1":
            return new MockResponse().setResponseCode(200);
          case "/users/2":
            return new MockResponse().setResponseCode(500);
          case "/users/3":
            return new MockResponse().setResponseCode(200).setBody("{\"id\": 1, \"name\":\"duke\"}");
        }
        return new MockResponse().setResponseCode(404);
      }
    };

    mockWebServer.setDispatcher(dispatcher);

    assertThrows(WebClientResponseException.class, () -> usersClient.getUserById(2L));
    assertThrows(WebClientResponseException.class, () -> usersClient.getUserById(4L));
  }

}
