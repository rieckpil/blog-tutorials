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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class GetAllPersonsIntegrationTest {

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

        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());

    }

    @Test
    @Sql("/testdata/FILL_FOUR_PERSONS.sql")
    public void testGetAllPersons() {

        ResponseEntity<Person[]> result = testRestTemplate.getForEntity("http://localhost:" + localPort +
                "/api/persons", Person[].class);

        List<Person> resultList = Arrays.asList(result.getBody());

        assertEquals(4, resultList.size());
        assertTrue(resultList.stream().map(p -> p.getName()).collect(Collectors.toList()).containsAll(Arrays.asList
                ("Mike", "Phil", "Duke", "Tom")));

    }

}
