package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SecondApplicationTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @MockBean
  private PersonService personService;

  @Test
  public void testPublicEndpoint() {
    when(personService.getPerson()).thenReturn("testPerson");

    String result = this.testRestTemplate
      .getForObject("/", String.class);

    assertEquals("testPerson", result);
  }
}
