package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@AutoConfigureMockMvc
@ContextConfiguration(initializers = CustomInitializer.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractIntegrationTest {

  @BeforeAll
  public static void commonSetup() {
    // e.g. provide WireMock stubs
  }
}
