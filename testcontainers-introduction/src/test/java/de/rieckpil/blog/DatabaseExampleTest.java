package de.rieckpil.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class DatabaseExampleTest {

  @Container
  static PostgreSQLContainer<?> database =
      new PostgreSQLContainer<>("postgres:14.0")
          .withDatabaseName("test")
          .withUsername("duke")
          .withPassword("s3cret");

  @Test
  void shouldConnectToDatabase() throws Exception {
    try (Connection connection =
        DriverManager.getConnection(
            database.getJdbcUrl(), database.getUsername(), database.getPassword())) {

      assertThat(connection.getMetaData().getDatabaseProductName()).isEqualTo("PostgreSQL");
    }
  }
}
