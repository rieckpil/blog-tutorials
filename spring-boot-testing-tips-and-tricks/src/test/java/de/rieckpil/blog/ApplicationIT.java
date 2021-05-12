package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationIT {

  @Autowired
  private WebTestClient webTestClient; // available with Spring WebFlux

  @Autowired
  private TestRestTemplate testRestTemplate; // available with Spring Web MVC

  @LocalServerPort
  private Integer port;

  @LocalManagementPort
  private Integer managementPort;

  @Test
  void printPortsInUse() {
    System.out.println(port);
    System.out.println(managementPort);
  }

  @Test
  void httpClientExample() {

    // no need for this
    WebClient webClient = WebClient.builder()
      .baseUrl("http://localhost:" + port)
      .build();

    this.webTestClient
      .get()
      .uri("/api/customers")
      .exchange()
      .expectStatus().is2xxSuccessful();

  }
}
