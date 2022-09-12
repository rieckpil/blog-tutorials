package de.rieckpil.blog;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

class GenericContainerExampleTest {

  static GenericContainer<?> nginx =
    new GenericContainer<>(DockerImageName.parse("nginx:1.23.1"));

  @Test
  void shouldStartContainer() {
    nginx.start();

    // container is running

    nginx.stop();
  }
}
