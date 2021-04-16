package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturnCustomerOne() {
    this.webTestClient.get().uri("/api/customers/1") // the base URL is already configured for us
      .accept(MediaType.APPLICATION_JSON)
      .exchange().expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.customerId").isNotEmpty()
      .jsonPath("$.name").isNotEmpty();
  }
}
