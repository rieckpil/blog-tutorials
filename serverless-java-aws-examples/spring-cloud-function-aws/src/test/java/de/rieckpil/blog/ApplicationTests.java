package de.rieckpil.blog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationTests {

  @Autowired
  private TestRestTemplate rest;

  @Test
  public void testUppercase() throws Exception {
    ResponseEntity<String> result = this.rest.exchange(
      RequestEntity.post(new URI("/uppercase")).body("hello"), String.class);
    assertEquals("HELLO", result.getBody());
  }

  @Test
  public void testLowercae() throws Exception {
    ResponseEntity<String> result = this.rest.exchange(
      RequestEntity.post(new URI("/lowercase")).body("HELLO"), String.class);
    assertEquals("hello", result.getBody());
  }

}
