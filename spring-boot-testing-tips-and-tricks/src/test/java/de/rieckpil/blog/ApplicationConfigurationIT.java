package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  properties = {
    "my.custom.property=inlined",
    "spring.main.banner-mode=off" // don't do this when Josh Long is pairing with you
  })
@ActiveProfiles("integration-test")
class ApplicationConfigurationIT {

  @Autowired
  private Environment environment;

  @MockBean
  private CustomerService customerService;

  @Test
  void shouldPrintConfigurationValues() {
    System.out.println(environment.getProperty("my.custom.property")); // inlined
    System.out.println(environment.getProperty("spring.application.name")); // integration-test
  }

  @Test
  void shouldRegisterUnknownUser() {
    Mockito.when(customerService.register("42")).thenReturn(7L);

    // test
  }
}
