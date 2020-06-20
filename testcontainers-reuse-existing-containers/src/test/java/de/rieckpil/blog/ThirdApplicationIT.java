package de.rieckpil.blog;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.AfterEach;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ThirdApplicationIT {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private TodoRepository todoRepository;

  static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:10-alpine")
    .withDatabaseName("test")
    .withUsername("duke")
    .withPassword("s3cret")
    .withReuse(true);

  @DynamicPropertySource
  static void datasourceConfig(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
  }

  @BeforeAll
  public static void beforeAll() {
    postgreSQLContainer.start();
  }

  @AfterEach
  public void cleanup() {
    this.todoRepository.deleteAll();
  }

  @Test
  void contextLoads() {
    this.todoRepository.saveAll(List.of(new Todo("Write blog post", LocalDateTime.now().plusDays(2)),
      new Todo("Clean appartment", LocalDateTime.now().plusDays(4))));

    ResponseEntity<ArrayNode> result = this.testRestTemplate.getForEntity("/todos", ArrayNode.class);
    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().isArray());
    assertEquals(2, result.getBody().size());
  }

}
