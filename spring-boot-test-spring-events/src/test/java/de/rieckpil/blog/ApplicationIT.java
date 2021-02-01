package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void shouldCreateUserAndPerformReporting() {

    ResponseEntity<Void> result = this.testRestTemplate
      .postForEntity("/api/users", "duke", Void.class);

    assertEquals(201, result.getStatusCodeValue());
    assertTrue(result.getHeaders().containsKey("Location"),
      "Response doesn't contain Location header");

    // additional assertion to verify the counter was incremented
    // additional assertion that a new message is part of the queue
  }
}
