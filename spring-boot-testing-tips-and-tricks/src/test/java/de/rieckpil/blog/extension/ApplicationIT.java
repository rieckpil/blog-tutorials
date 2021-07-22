package de.rieckpil.blog.extension;

import de.rieckpil.blog.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  @Autowired
  private CustomerService customerService;

  @Test
  void shouldInjectBean() {
    assertNotNull(customerService);
  }
}
