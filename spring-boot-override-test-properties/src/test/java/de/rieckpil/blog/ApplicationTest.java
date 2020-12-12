package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "welcome.message=Spring Boot Test Hello World!")
class ApplicationTest {

  @Value("${welcome.message}")
  private String welcomeMessage;

  @Test
  void contextLoads() {
    assertEquals("Spring Boot Test Hello World!", welcomeMessage);
  }
}
