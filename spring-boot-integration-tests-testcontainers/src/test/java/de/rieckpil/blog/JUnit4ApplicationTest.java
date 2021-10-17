package de.rieckpil.blog;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = JUnit4ApplicationTest.Initializer.class)
public class JUnit4ApplicationTest {

  @ClassRule
  public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
    .withPassword("inmemory")
    .withUsername("inmemory");

  public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues values = TestPropertyValues.of(
        "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
        "spring.datasource.password=" + postgreSQLContainer.getPassword(),
        "spring.datasource.username=" + postgreSQLContainer.getUsername()
      );
      values.applyTo(configurableApplicationContext);
    }
  }

  @Test
  public void contextLoads() {
  }
}
