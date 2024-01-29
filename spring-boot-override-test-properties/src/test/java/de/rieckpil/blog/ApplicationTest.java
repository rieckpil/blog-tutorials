package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "welcome.message=Spring Boot Test Hello World!")
class ApplicationTest {

  @Value("${welcome.message}")
  private String welcomeMessage;

  @Test
  void contextLoads() {
    assertEquals("Spring Boot Test Hello World!", welcomeMessage);
  }
}
