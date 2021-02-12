package de.rieckpil.blog.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ModuleContainerTest {

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer<>("postgres:12")
    .withUsername("duke")
    .withPassword("secret")
    .withInitScript("config/INIT.sql")
    .withDatabaseName("tescontainers");

  @Test
  void testPostgreSQLModule() {
    System.out.println(database.getJdbcUrl());
    System.out.println(database.getTestQueryString());
  }
}
