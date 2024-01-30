package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

  @Autowired private WebTestClient webTestClient;

  @Test
  public void actuatorEndpointContainsGitInformation() {
    this.webTestClient
        .get()
        .uri("/actuator/info")
        .exchange()
        .expectBody()
        .jsonPath("$.git.branch")
        .isNotEmpty()
        .jsonPath("$.git.commit.id")
        .isNotEmpty()
        .jsonPath("$.git.commit.time")
        .isNotEmpty();
  }
}
