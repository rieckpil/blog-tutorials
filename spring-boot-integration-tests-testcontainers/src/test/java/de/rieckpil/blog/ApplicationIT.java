package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// JUnit 5 example with Spring Boot >= 2.2.6
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIT {

  @Container
  public static PostgreSQLContainer postgreSQLContainer =
      new PostgreSQLContainer().withPassword("inmemory").withUsername("inmemory");

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
  }

  @Test
  public void contextLoads() {}
}
