package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

class ApplicationIT extends BaseIT {

  @Autowired private TestRestTemplate testRestTemplate;

  @Test
  void contextLoads() {
    ResponseEntity<JsonNode> result = testRestTemplate.getForEntity("/todos", JsonNode.class);
    assertEquals(200, result.getStatusCodeValue());
  }
}
