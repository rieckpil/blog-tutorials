package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ManualSetupIT {

  private static WireMockServer wireMockServer;

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

  private void stubForTodosResponse() {
    wireMockServer.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[]"))
    );
  }

  private void stubForFailingTodosResponse() {
    wireMockServer.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withStatus(500)
          .withBody("Server unavailable"))
    );
  }
}
