package de.rieckpil.blog;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

class GenericContainerExampleTest {

  static GenericContainer<?> database =
    new GenericContainer<>(DockerImageName.parse("postgres:14.0"));

  @Test
  void shouldConnectToDatabase() {
    database.start();

    // access to the database

    database.stop();
  }
}
