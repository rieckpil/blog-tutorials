package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

@Disabled("Will fail because other IT already launches on the defined port")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class SecondDefinedPortApplicationTest {

  @LocalServerPort private Integer port;

  @MockBean private CustomerService customerService;

  @Test
  void accessApplication() {
    System.out.println(port);
  }
}
