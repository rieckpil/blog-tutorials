package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseIT {

  static final PostgreSQLContainer<?> postgreSQLContainer;

  static {
    postgreSQLContainer =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:13"))
            .withDatabaseName("test")
            .withUsername("duke")
            .withPassword("s3cret")
            .withReuse(true);

    postgreSQLContainer.start();
  }

  @DynamicPropertySource
  static void datasourceConfig(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
  }
}
