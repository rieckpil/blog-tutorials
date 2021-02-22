package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class KeycloakExampleTest {

  @Container
  static GenericContainer<?> keycloak =
    new GenericContainer<>(DockerImageName.parse("jboss/keycloak:11.0.0"))
      .waitingFor(Wait.forHttp("/auth").forStatusCode(200))
      .withExposedPorts(8080)
      //.withCopyFileToContainer(MountableFile.forHostPath("/tmp/dump.json"), "/tmp/dump.json")
      .withClasspathResourceMapping("/keycloak/dump.json", "/tmp/dump.json", BindMode.READ_ONLY)
      .withEnv(Map.of(
        "KEYCLOAK_USER", "testcontainers",
        "KEYCLOAK_PASSWORD", "testcontainers",
        "JAVA_OPTS", "-Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=/tmp/dump.json",
        "DB_VENDOR", "h2"
      ));

  @Test
  void testKeycloak () {
    assertTrue(keycloak.isRunning());
  }
}
