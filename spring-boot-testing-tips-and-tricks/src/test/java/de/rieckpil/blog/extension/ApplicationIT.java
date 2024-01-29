package de.rieckpil.blog.extension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import de.rieckpil.blog.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  @Autowired
  // resolved via DependencyInjectionTestExecutionListener
  private CustomerService customerService;

  @Test
  void needsEnvironmentBeanToVerifySomething(
      @Autowired Environment environment // resolved via the SpringExtension
      ) {
    assertNotNull(environment);
  }
}
