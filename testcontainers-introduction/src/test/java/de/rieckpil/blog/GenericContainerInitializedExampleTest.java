package de.rieckpil.blog;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

  @Testcontainers
  class GenericContainerInitializedExampleTest {

    @Container
    static GenericContainer<?> database =
      new GenericContainer<>(DockerImageName.parse("postgres:14.0"))
        .withEnv("POSTGRES_PASSWORD", "secret")
        .withEnv("POSTGRES_USER", "duke")
        .withEnv("POSTGRES_DB", "test")
        .withExposedPorts(5432)
        .waitingFor(Wait.forListeningPort());

    @Test
    void shouldConnectToDatabase() throws Exception {
      try (Connection connection = DriverManager
        .getConnection("jdbc:postgresql://localhost:" +
          database.getMappedPort(5432) + "/test", "duke", "secret")) {

        assertThat(connection.getMetaData().getDatabaseProductName())
          .isEqualTo("PostgreSQL");
      }
    }
  }


