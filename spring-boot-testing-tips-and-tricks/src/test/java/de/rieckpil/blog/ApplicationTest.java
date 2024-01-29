package de.rieckpil.blog;

// JUnit Jupiter (part of JUnit 5)
import static org.springframework.boot.test.context.SpringBootTest.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @LocalServerPort private Integer port;

  @Autowired private TestRestTemplate testRestTemplate;

  @Test
  void accessApplication() {
    System.out.println(port);
  }
}
