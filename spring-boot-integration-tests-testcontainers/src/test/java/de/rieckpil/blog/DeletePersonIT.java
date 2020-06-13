package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// JUnit 5 example with Spring Boot < 2.2.6
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DeletePersonIT.Initializer.class)
public class DeletePersonIT {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
    .withPassword("inmemory")
    .withUsername("inmemory");

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  public TestRestTemplate testRestTemplate;

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
  @Sql("/testdata/FILL_FOUR_PERSONS.sql")
  public void testDeletePerson() {
    testRestTemplate.delete("/api/persons/1");
    assertEquals(3, personRepository.findAll().size());
    assertFalse(personRepository.findAll().contains("Phil"));

  }
}
