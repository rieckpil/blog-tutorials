package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationIT extends BaseIT{

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void contextLoads() {
    ResponseEntity<JsonNode> result = testRestTemplate.getForEntity("/todos", JsonNode.class);
    assertEquals(200, result.getStatusCodeValue());
  }

}
