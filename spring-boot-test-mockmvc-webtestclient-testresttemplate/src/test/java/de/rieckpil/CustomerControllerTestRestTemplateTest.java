package de.rieckpil;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTestRestTemplateTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldReturnListOfAllCustomers() {

    ResponseEntity<List<Customer>> response = this.testRestTemplate
      .exchange("/api/customers", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
      });

    assertThat(response.getStatusCodeValue())
      .isEqualTo(200);

    assertThat(response.getBody())
      .hasSizeGreaterThan(0);
  }

  @Test
  void shouldCreateNewCustomers() {

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

    HttpEntity<String> requestEntity = new HttpEntity<>("""
      {
        "firstName": "Mike",
        "lastName": "Thomson",
        "id": 43
       }
      """,
      requestHeaders
    );

    ResponseEntity<Void> response = this.testRestTemplate
      .exchange("/api/customers", HttpMethod.POST, requestEntity, Void.class);

    assertThat(response.getStatusCodeValue())
      .isEqualTo(201);
  }
}
