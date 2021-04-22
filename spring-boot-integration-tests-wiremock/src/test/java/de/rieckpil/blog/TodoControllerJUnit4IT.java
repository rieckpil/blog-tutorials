package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodoControllerJUnit4IT {

  @Autowired
  private WebTestClient webTestClient;

  @ClassRule
  public static WireMockClassRule wireMockRule =
    new WireMockClassRule(WireMockConfiguration.wireMockConfig().dynamicPort());

  @Rule
  public WireMockClassRule instanceRule = wireMockRule;

  @LocalServerPort
  private Integer port;

  @Autowired
  private WebClientConfig config;

  @DynamicPropertySource
  static void webClientConfig(DynamicPropertyRegistry registry) {
    registry.add("todo_base_url", wireMockRule::baseUrl);
  }

  @Test
  public void testGetAllTodosShouldReturnDataFromClient() {

    System.out.println("Mappings: " + wireMockRule.getStubMappings().size());

    this.wireMockRule.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[{\"userId\": 1,\"id\": 1,\"title\": \"Learn Spring Boot 3.0\", \"completed\": false}," +
            "{\"userId\": 1,\"id\": 2,\"title\": \"Learn WireMock\", \"completed\": true}]"))
    );

    this.webTestClient
      .get()
      .uri("http://localhost:" + port + "/api/todos")
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
  public void testGetAllTodosShouldPropagateErrorMessageFromClient() {

    System.out.println("Mappings: " + wireMockRule.getStubMappings().size());

    this.wireMockRule.stubFor(
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
