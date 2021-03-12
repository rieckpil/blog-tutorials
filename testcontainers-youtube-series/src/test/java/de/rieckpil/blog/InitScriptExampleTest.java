package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

@Testcontainers
public class InitScriptExampleTest {

  @Container
  static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:12")
    .withUsername("testcontainers")
    .withPassword("testcontainers")
    .withInitScript("database/INIT.sql")
    .withDatabaseName("testcontainers");

  @Test
  void testPostgreSQLModule() throws SQLException {
    try (Connection connection = DriverManager
      .getConnection(database.getJdbcUrl(), "testcontainers", "testcontainers");
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages")) {
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          System.out.println(resultSet.getString("content"));
        }
      }
    }
  }
}
