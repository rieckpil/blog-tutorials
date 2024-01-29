package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/custom.properties")
class ApplicationPropertySourceTest {

  @Value("${welcome.message}")
  private String welcomeMessage;

  @Test
  void contextLoads() {
    assertEquals("Custom Property Source Hello World!", welcomeMessage);
  }
}
