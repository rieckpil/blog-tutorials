package de.rieckpil.blog.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

@Testcontainers
public class DockerComposeTest {

  @Container
  static DockerComposeContainer<?> environment =
    new DockerComposeContainer<>(new File("docker-compose.yml"))
      .withExposedService("database_1", 5432, Wait.forListeningPort())
      .withExposedService("keycloak_1", 8080,
        Wait.forHttp("/auth").forStatusCode(200)
        .withStartupTimeout(Duration.ofSeconds(30)));


  @Test
  void dockerComposeTest() {
    System.out.println(environment.getServicePort("database_1", 5432));
    System.out.println(environment.getServicePort("keycloak_1", 8080));
  }

}
