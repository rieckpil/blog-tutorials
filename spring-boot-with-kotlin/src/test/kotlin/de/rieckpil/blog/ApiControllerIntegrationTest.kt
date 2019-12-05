package de.rieckpil.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiControllerIntegrationTest(
  @Autowired val webTestClient: WebTestClient
) {

  @Test
  fun `should get todos with status code 200`() {
    this.webTestClient
      .get()
      .uri("/api/todos")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody()
      .jsonPath("$[0].userId").isEqualTo(1)
      .jsonPath("$[0].id").isEqualTo(1)
      .jsonPath("$[0].title").isEqualTo("delectus aut autem")
      .jsonPath("$[0].completed").isEqualTo(false)
  }

  @Test
  fun `should get three persons right after startup`() {
    this.webTestClient
      .get()
      .uri("/api/persons")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().is2xxSuccessful
      .expectBody()
      .jsonPath("$.length()").isEqualTo(3)
  }
}
