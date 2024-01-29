package de.rieckpil.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(
    webEnvironment = WebEnvironment.DEFINED_PORT,
    properties = {"server.port=8042", "management.server.port=9042"})
class ApplicationDefinedPortIT {

  @LocalServerPort private Integer port;

  @LocalManagementPort private Integer managementPort;

  @Test
  void printPortsInUse() {
    System.out.println(port); // 8042
    System.out.println(managementPort); // 9042
  }
}
