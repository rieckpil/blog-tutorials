package de.rieckpil.blog.testcontainers;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CreatePersonIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer().withPassword("inmemory")
            .withUsername("inmemory");

    @LocalServerPort
    private int localPort;

    @Autowired
    private PersonRepository personRepository;

    public TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeClass
    public static void beforeClass() {

        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());

    }

    @Test
    public void testRestEndpointForAllPersons() {

        Map<String, String> requestParams = new HashMap<String, String>();

        Person requestBody = new Person();
        requestBody.setName("rieckpil");

        assertEquals(0, personRepository.findAll().size());

        ResponseEntity<Person> result = testRestTemplate.postForEntity("http://localhost:" + localPort +
                "/api/persons", requestBody, Person.class, requestParams);

        assertNotNull(result);
        assertNotNull(result.getBody().getId());
        assertEquals("rieckpil", result.getBody().getName());
        assertEquals(1, personRepository.findAll().size());
        assertEquals("rieckpil", personRepository.findAll().get(0).getName());

    }

}
