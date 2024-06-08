package de.rieckpil.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

class GenericContainerExampleTest {

  static GenericContainer<?> nginx = new GenericContainer<>(DockerImageName.parse("nginx:1.23.1"));

  @Test
  void shouldStartContainer() {
    nginx.start();

    // container is running
    assertThat(nginx.isRunning()).isTrue();

    nginx.stop();
  }
}
