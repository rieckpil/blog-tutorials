package de.rieckpil.blog.exercise10;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class AdvancedTestcontainersTest {

  @Container
  static GenericContainer<?> keycloakContainer =
    new GenericContainer<>(DockerImageName.parse("jboss/keycloak:14.0.0"))
      .withExposedPorts(8080)
      .withStartupTimeout(Duration.ofSeconds(30))
      .waitingFor(Wait.forHttp("/auth").forStatusCode(200));

  @Test
  void shouldStartCustomDockerContainer() {
    assertTrue(keycloakContainer.isRunning());
  }
}
