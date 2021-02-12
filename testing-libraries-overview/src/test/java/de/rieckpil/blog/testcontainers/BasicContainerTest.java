package de.rieckpil.blog.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.Map;

@Testcontainers
public class BasicContainerTest {

  @Container
  static GenericContainer<?> keycloak =
    new GenericContainer<>(DockerImageName.parse("jboss/keycloak:11.0.0"))
      .waitingFor(Wait.forHttp("/auth").forStatusCode(200))
      .withExposedPorts(8080)
      .withClasspathResourceMapping("/config/test.txt", "/tmp/test.txt", BindMode.READ_WRITE)
      .withEnv(Map.of(
        "KEYCLOAK_USER", "testcontainers",
        "KEYCLOAK_PASSWORD", "testcontainers",
        "DB_VENDOR", "h2"
      ));

  @Test
  void testWithKeycloak() throws IOException, InterruptedException {

    ExecResult execResult = keycloak
      .execInContainer("/bin/sh", "-c", "echo \"Admin user is $KEYCLOAK_USER\"");

    System.out.println("Result: " + execResult.getStdout());
    System.out.println("Keycloak is running on port: " + keycloak.getMappedPort(8080));
  }
}
