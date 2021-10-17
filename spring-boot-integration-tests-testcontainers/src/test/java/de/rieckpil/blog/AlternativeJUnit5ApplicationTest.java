package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// JUnit 5 example with Spring Boot < 2.2.6
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AlternativeJUnit5ApplicationTest.Initializer.class)
class AlternativeJUnit5ApplicationTest {

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
    .withPassword("inmemory")
    .withUsername("inmemory");

  public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues values = TestPropertyValues.of(
        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
        "spring.datasource.password=" + postgreSQLContainer.getPassword(),
        "spring.datasource.username=" + postgreSQLContainer.getUsername());
      values.applyTo(configurableApplicationContext);
    }
  }

  @Test
  void contextLoads() {
  }
}
