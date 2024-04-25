package de.rieckpil.helper;

import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class MySQLDataSourceInitializer implements BeforeAllCallback {

  private static final AtomicBoolean INITIAL_INVOCATION = new AtomicBoolean(Boolean.TRUE);

  private final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8");
  private final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MYSQL_IMAGE);

  @Override
  public void beforeAll(final ExtensionContext context) {
    if (INITIAL_INVOCATION.getAndSet(Boolean.FALSE)) {
      log.info("Creating datasource container : {}", MYSQL_IMAGE);
      mySQLContainer.start();
      addDataSourceProperties();
      log.info("Successfully started datasource container : {}", MYSQL_IMAGE);
    }
  }

  private void addDataSourceProperties() {
    System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
    System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
    System.setProperty("spring.test.database.replace", Replace.NONE.name());
  }
}
