package de.rieckpil.blog.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIT {

  @LocalServerPort
  private Integer port;

  @Test
  void restAssuredExample() {

    given()
      .accept("application/json")
    .when()
      .get(URI.create("http://localhost:" + port + "/api/customers"))
    .then()
      .body("[0].username", equalTo("duke42"));

  }
}
