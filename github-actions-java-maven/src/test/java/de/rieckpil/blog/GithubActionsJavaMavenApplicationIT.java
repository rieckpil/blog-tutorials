package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {GithubActionsJavaMavenApplicationIT.Initializer.class})
class GithubActionsJavaMavenApplicationIT {

  @Autowired
  private WebTestClient webTestClient;

  @Container
  private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
    .withDatabaseName("github")
    .withUsername("github")
    .withPassword("actions");

  @Test
  void shouldReturnThreeDefaultUser() {
    this.webTestClient
      .get()
      .uri("/api/users")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .jsonPath("$.length()").isEqualTo(3);
  }

  static class Initializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
        "spring.datasource.username=" + postgreSQLContainer.getUsername(),
        "spring.datasource.password=" + postgreSQLContainer.getPassword()
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}
