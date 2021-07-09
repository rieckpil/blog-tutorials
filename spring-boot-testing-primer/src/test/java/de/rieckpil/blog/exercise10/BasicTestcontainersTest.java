package de.rieckpil.blog.exercise10;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class BasicTestcontainersTest {

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
    new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"))
    .withDatabaseName("test")
    .withUsername("duke")
    .withPassword("superSecret");

  @Test
  void shouldStartPostgreSQLDatabase() {
    assertTrue(postgreSQLContainer.isRunning());
  }
}
