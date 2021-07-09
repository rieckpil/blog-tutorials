package de.rieckpil.blog.exercise11;

import java.util.List;

import de.rieckpil.blog.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
  "spring.security.user.name=duke",
  "spring.security.user.password=duke42"
})
class BasicIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldFetchListOfCustomers() {
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("duke", "duke42");

    HttpEntity<List<Customer>> request = new HttpEntity<>(headers);

    ResponseEntity<List<Customer>> result = this.testRestTemplate
      .exchange("/api/customers", HttpMethod.GET, request, new ParameterizedTypeReference<>() {});

    assertEquals(200, result.getStatusCodeValue());
  }
}
