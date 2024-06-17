package de.rieckpil;

import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

class MySQLDatabaseInitializer implements BeforeAllCallback {

  private static final AtomicBoolean INITIAL_INVOCATION = new AtomicBoolean(Boolean.TRUE);

  private static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:8");
  private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MYSQL_IMAGE);

  @Override
  public void beforeAll(final ExtensionContext context) {
    if (INITIAL_INVOCATION.getAndSet(Boolean.FALSE)) {
      mySQLContainer.start();
      addDataSourceProperties();
    }
  }

  private void addDataSourceProperties() {
    System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
    System.setProperty("spring.datasource.password", mySQLContainer.getPassword());
    System.setProperty("spring.test.database.replace", Replace.NONE.name());
  }
}
