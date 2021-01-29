package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = WireMockInitializer.class)
class ApplicationTests {

  @Test
  void contextLoads() {
  }

}
