package de.rieckpil.blog.testcontainers;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class BasicContainerTest {

  @Container
  static GenericContainer<?> keycloak =
      new GenericContainer<>(DockerImageName.parse("quay.io/keycloak/keycloak:24.0"))
          .withCommand("start-dev")
          .waitingFor(
              Wait.forHttp("/realms/master")
                  .forStatusCode(200)
                  .withStartupTimeout(Duration.ofMinutes(2)))
          .withExposedPorts(8080)
          .withClasspathResourceMapping("/config/test.txt", "/tmp/test.txt", BindMode.READ_WRITE)
          .withEnv(
              Map.of(
                  "KEYCLOAK_ADMIN", "testcontainers",
                  "KEYCLOAK_ADMIN_PASSWORD", "testcontainers"));

  @Test
  void testWithKeycloak() throws IOException, InterruptedException {

    ExecResult execResult =
        keycloak.execInContainer("/bin/sh", "-c", "echo \"Admin user is $KEYCLOAK_ADMIN\"");

    System.out.println("Result: " + execResult.getStdout());
    System.out.println("Keycloak is running on port: " + keycloak.getMappedPort(8080));
  }
}
