package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
class TodoControllerJUnit5IT {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private WireMockServer wireMockServer;

  @AfterEach
  void resetAll() {
    wireMockServer.resetAll();
  }

  @Test
  void basicWireMockExample() {

    wireMockServer.stubFor(
      WireMock.get(WireMock.urlEqualTo("/todos"))
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBodyFile("todo-api/response-200.json"))
    );

    this.webTestClient
      .get()
      .uri("/api/todos")
      .exchange()
      .expectStatus().isOk()
      .expectBody().jsonPath("$.length()").isEqualTo(3)
      .jsonPath("$[0].title").isEqualTo("delectus aut autem");
  }
}
