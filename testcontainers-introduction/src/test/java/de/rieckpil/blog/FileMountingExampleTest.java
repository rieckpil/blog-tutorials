package de.rieckpil.blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class FileMountingExampleTest {

  @Container
  static PostgreSQLContainer<?> database =
    new PostgreSQLContainer<>("postgres:14.0")
      .withDatabaseName("test")
      .withUsername("duke")
      .withPassword("s3cret")
      .withClasspathResourceMapping("/database-init", "/docker-entrypoint-initdb.d/", BindMode.READ_ONLY);

  @Test
  void shouldConnectToDatabase() throws Exception {
    try (Connection connection = DriverManager
      .getConnection(database.getJdbcUrl(), database.getUsername(), database.getPassword())) {

      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM orders");

      while (resultSet.next()) {
        assertThat(resultSet.getString(2))
          .isEqualTo("XYZ123");
      }
    }
  }
}
