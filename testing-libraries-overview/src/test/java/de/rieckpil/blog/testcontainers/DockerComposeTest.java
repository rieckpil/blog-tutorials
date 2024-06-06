package de.rieckpil.blog.testcontainers;

import java.io.File;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class DockerComposeTest {

  @Container
  static ComposeContainer environment =
      new ComposeContainer(new File("docker-compose.yml"))
          .withExposedService("database-1", 5432, Wait.forListeningPort())
          .withExposedService(
              "keycloak-1",
              8080,
              Wait.forHttp("/auth").forStatusCode(200).withStartupTimeout(Duration.ofSeconds(30)));

  @Test
  void dockerComposeTest() {
    System.out.println(environment.getServicePort("database-1", 5432));
    System.out.println(environment.getServicePort("keycloak-1", 8080));
  }
}
