package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
class TodoControllerIT {

  @Autowired
  private WireMockServer wireMockServer;

  @Autowired
  private WebTestClient webTestClient;

  @LocalServerPort
  private Integer port;

  @AfterEach
  public void afterEach() {
    this.wireMockServer.resetAll();
  }

@Test
void testGetAllTodosShouldReturnDataFromClient() {
  this.wireMockServer.stubFor(
    WireMock.get("/todos")
      .willReturn(aResponse()
        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .withBody("[{\"userId\": 1,\"id\": 1,\"title\": \"Learn Spring Boot 3.0\", \"completed\": false}," +
          "{\"userId\": 1,\"id\": 2,\"title\": \"Learn WireMock\", \"completed\": true}]"))
  );

  this.webTestClient
    .get()
    .uri("/api/todos")
    .exchange()
    .expectStatus()
    .is2xxSuccessful()
    .expectBody()
    .jsonPath("$[0].title")
    .isEqualTo("Learn Spring Boot 3.0")
    .jsonPath("$.length()")
    .isEqualTo(2);
}

  @Test
  void testGetAllTodosShouldPropagateErrorMessageFromClient() {
    this.wireMockServer.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withStatus(403)
          .withFixedDelay(2000)) // milliseconds
    );

    this.webTestClient
      .get()
      .uri("http://localhost:" + port + "/api/todos")
      .exchange()
      .expectStatus()
      .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR_500);
  }
}
