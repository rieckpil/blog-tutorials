package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void contextLoads() {
    ResponseEntity<Object> actuatorResult =
      this.testRestTemplate.getForEntity("/actuator/health", Object.class);

    assertEquals(200, actuatorResult.getStatusCodeValue());
  }

}
