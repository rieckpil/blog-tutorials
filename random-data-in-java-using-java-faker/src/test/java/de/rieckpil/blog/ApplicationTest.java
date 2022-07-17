package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void contextLoads() {

    this.webTestClient
      .get()
      .uri("/random/persons")
      .exchange()
      .expectStatus().is2xxSuccessful();

    this.webTestClient
      .get()
      .uri("/random/foods")
      .exchange()
      .expectStatus().is2xxSuccessful();

    this.webTestClient
      .get()
      .uri("/random/books")
      .exchange()
      .expectStatus().is2xxSuccessful();

  }
}
