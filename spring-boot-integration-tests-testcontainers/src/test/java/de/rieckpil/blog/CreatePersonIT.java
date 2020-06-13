package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

// JUnit 5 example with Spring Boot >= 2.2.6
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreatePersonIT {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
    .withPassword("inmemory")
    .withUsername("inmemory");

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  public TestRestTemplate testRestTemplate;

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
  }

  @Test
  public void testRestEndpointForAllPersons() {

    Person requestBody = new Person();
    requestBody.setName("rieckpil");

    assertEquals(0, personRepository.findAll().size());

    ResponseEntity<Person> result = testRestTemplate.postForEntity("/api/persons", requestBody, Person.class);

    assertNotNull(result);
    assertNotNull(result.getBody().getId());
    assertEquals("rieckpil", result.getBody().getName());
    assertEquals(1, personRepository.findAll().size());
    assertEquals("rieckpil", personRepository.findAll().get(0).getName());
  }

}
