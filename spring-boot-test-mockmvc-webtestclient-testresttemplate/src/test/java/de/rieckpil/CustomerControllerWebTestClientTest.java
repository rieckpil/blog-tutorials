package de.rieckpil;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerWebTestClientTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturnListOfAllCustomers() {
    EntityExchangeResult<List<Customer>> result = this.webTestClient
      .get()
      .uri("/api/customers")
      .header(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus()
      .is2xxSuccessful()
      .expectBodyList(Customer.class)
      .returnResult();

    assertThat(result.getResponseBody())
      .hasSizeGreaterThan(1);
  }

  @Test
  void shouldCreateNewCustomers() {
    this.webTestClient
      .post()
      .uri("/api/customers")
      .bodyValue("""
         {
        "firstName": "Mike",
        "lastName": "Thomson",
        "id": 43
       }
        """)
      .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus()
      .isCreated();
  }
}
