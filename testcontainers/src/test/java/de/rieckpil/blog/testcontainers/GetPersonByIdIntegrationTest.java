package de.rieckpil.blog.testcontainers;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = GetPersonByIdIntegrationTest.Initializer.class)
public class GetPersonByIdIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withPassword("inmemory")
            .withUsername("inmemory");

    @LocalServerPort
    private int localPort;

    @Autowired
    private PersonRepository personRepository;

    public TestRestTemplate testRestTemplate = new TestRestTemplate();

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

        ResponseEntity<Person> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                "/api/persons/42", Person.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody().getName());
        assertNull(result.getBody().getId());

    }

    @Test
    @Sql("/testdata/FILL_FOUR_PERSONS.sql")
    public void testExistingPersonById() {

        System.out.println(personRepository.findAll().size());


        ResponseEntity<Person> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                "/api/persons/1", Person.class);


        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Phil", result.getBody().getName());
        assertEquals(1l, result.getBody().getId().longValue());


    }

}
