package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerJUnit4IT {

  @Autowired
  private WebTestClient webTestClient;

  @ClassRule
  public static WireMockClassRule wireMockClassRule =
    new WireMockClassRule(WireMockConfiguration.wireMockConfig().dynamicPort());

  @Rule
  public WireMockClassRule methodRule = wireMockClassRule;

  @DynamicPropertySource
  static void webClientConfig(DynamicPropertyRegistry registry) {
    registry.add("todo_base_url", wireMockClassRule::baseUrl);
  }

  @Test
  public void basicWireMockExample() {

    System.out.println("Stored stub mappings: " + wireMockClassRule.getStubMappings());

    wireMockClassRule.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[]"))
    );

    this.webTestClient
      .get()
      .uri("/api/todos")
      .exchange()
      .expectStatus().isOk()
      .expectBody().jsonPath("$.length()").isEqualTo(0);
  }

  @Test
  public void basicWireMockExampleTwo() {

    System.out.println("Stored stub mappings: " + wireMockClassRule.getStubMappings());

    wireMockClassRule.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[]"))
    );

    this.webTestClient
      .get()
      .uri("/api/todos")
      .exchange()
      .expectStatus().isOk()
      .expectBody().jsonPath("$.length()").isEqualTo(0);
  }
}
