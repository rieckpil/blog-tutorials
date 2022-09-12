package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.Container.ExecResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class CommandExecutingExampleTest {

  @Container
  static PostgreSQLContainer<?> database =
    new PostgreSQLContainer<>("postgres:14.0")
      .withDatabaseName("test")
      .withUsername("duke")
      .withPassword("s3cret");

  @Test
  void shouldExecuteCommand() throws Exception {
    ExecResult commandResult = database.execInContainer("ls", "-al", "/");

    String stdout = commandResult.getStdout();
    int exitCode = commandResult.getExitCode();

    System.out.println(stdout);

    assertThat(stdout).contains("root");
    assertThat(exitCode).isZero();
  }
}
