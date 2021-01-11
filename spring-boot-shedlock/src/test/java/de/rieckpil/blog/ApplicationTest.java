package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer<>("postgres:12")
    .withUsername("spring")
    .withPassword("secret")
    .withDatabaseName("spring");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", database::getJdbcUrl);
    registry.add("spring.datasource.password", database::getPassword);
    registry.add("spring.datasource.username", database::getUsername);
  }

  @Test
  void contextLoads() {
  }

}
