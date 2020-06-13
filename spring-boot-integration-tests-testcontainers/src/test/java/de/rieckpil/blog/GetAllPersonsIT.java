package de.rieckpil.blog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// JUnit 4.12 example
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = GetAllPersonsIT.Initializer.class)
public class GetAllPersonsIT {

  @ClassRule
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
    .withPassword("inmemory")
    .withUsername("inmemory");

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
  public void testGetAllPersons() {

    ResponseEntity<Person[]> result = testRestTemplate.getForEntity("/api/persons", Person[].class);

    List<Person> resultList = Arrays.asList(result.getBody());

    assertEquals(4, resultList.size());
    assertTrue(resultList.stream().map(p -> p.getName()).collect(Collectors.toList()).containsAll(Arrays.asList
      ("Mike", "Phil", "Duke", "Tom")));

  }

}
