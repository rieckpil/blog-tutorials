package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ManualSetupIT {

  @Autowired
  private WebTestClient webTestClient;

  private static WireMockServer wireMockServer;

  @DynamicPropertySource
  static void webClientConfig(DynamicPropertyRegistry registry) {
    registry.add("todo_base_url", wireMockServer::baseUrl);
  }

  @BeforeAll
  static void startWireMock() {
    wireMockServer = new WireMockServer(WireMockConfiguration
      .wireMockConfig()
      .dynamicPort());
    wireMockServer.start();
  }

  @AfterAll
  static void stopWireMock() {
    wireMockServer.stop();
  }

  @Test
  void shouldStartWireMock() {

    wireMockServer.getStubMappings().forEach(StubMapping::toString);

    assertNotNull(wireMockServer);
    assertTrue(wireMockServer.isRunning());
  }

  @Test
  void verifyRequests() {

    stubForTodosResponse();

    webTestClient
      .get()
      .uri("/api/todos")
      .exchange()
      .expectStatus().isOk();

    wireMockServer.verify(exactly(1), getRequestedFor(urlEqualTo("/todos"))
      .withHeader("Accept", equalTo("application/json")));

    List<ServeEvent> allServeEvents = wireMockServer.getAllServeEvents();

    LoggedRequest request = allServeEvents.get(0).getRequest();

    assertEquals("", request.getBodyAsString());
    assertEquals("http", request.getScheme());
    assertEquals("GET", request.getMethod().getName());

    List<LoggedRequest> unmatchedRequests = wireMockServer.findAllUnmatchedRequests();

    assertEquals(0, unmatchedRequests.size());

  }

  private void stubForTodosResponse() {

    // anyUrl
    // urlPathMatching

    wireMockServer.stubFor(
      WireMock.get(urlEqualTo("/todos"))
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[]"))
    );
  }

  private void stubForFailingTodosResponse() {
    wireMockServer.stubFor(
      WireMock.get(urlEqualTo("/todos"))
        .willReturn(aResponse()
          .withStatus(500)
          .withBody("Server unavailable"))
    );
  }
}
