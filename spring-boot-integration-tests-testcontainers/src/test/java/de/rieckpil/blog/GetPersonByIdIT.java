package de.rieckpil.blog;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testcontainers.containers.PostgreSQLContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.boot.test.context.SpringBootTest.*;

// JUnit 4.12 example
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = GetPersonByIdIT.Initializer.class)
public class GetPersonByIdIT {

  @ClassRule
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
  public void testNotExistingPersonByIdShouldReturn404() {

    ResponseEntity<Person> result = testRestTemplate.getForEntity("/api/persons/42", Person.class);

    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    assertNull(result.getBody().getName());
    assertNull(result.getBody().getId());

  }

  @Test
  @Sql("/testdata/FILL_FOUR_PERSONS.sql")
  public void testExistingPersonById() {
    System.out.println(personRepository.findAll().size());

    ResponseEntity<Person> result = testRestTemplate.getForEntity("/api/persons/1", Person.class);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals("Phil", result.getBody().getName());
    assertEquals(1l, result.getBody().getId().longValue());
  }

}
