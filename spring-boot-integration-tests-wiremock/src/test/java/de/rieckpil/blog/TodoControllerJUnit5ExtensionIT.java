package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TodoControllerJUnit5ExtensionIT {

  @Autowired
  private WebTestClient webTestClient;

  @RegisterExtension
  static WireMockExtension wireMockServer = WireMockExtension.newInstance()
    .options(wireMockConfig().dynamicPort())
    .build();

@DynamicPropertySource
static void configureProperties(DynamicPropertyRegistry registry) {
  registry.add("todo_base_url", wireMockServer::baseUrl);
}

  @AfterEach
  void resetAll() {
    // we're using one WireMock server for the test class (see static on the WireMockExtension definition)
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
  @Test
  void testGetAllTodosShouldPropagateErrorMessageFromClient() {
    wireMockServer.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withStatus(403)
          .withFixedDelay(2000)) // milliseconds
    );

    this.webTestClient
      .get()
      .uri("/api/todos")
      .exchange()
      .expectStatus()
      .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR_500);
  }
}
