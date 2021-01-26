package de.rieckpil.blog;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT,
  properties = {
  "spring.security.user.name=duke",
  "spring.security.user.password=secret",
  "spring.security.user.roles=ADMIN"
})
class ApplicationTest {

  @LocalServerPort
  private Integer port;

  @Test
  void shouldCreateBook() {

    ExtractableResponse<Response> response = RestAssured
      .given()
        .filter(new RequestLoggingFilter())
        .auth().preemptive().basic("duke", "secret")
        .contentType("application/json")
        .body("{\"title\": \"Effective Java\", \"isbn\":\"978-0-13-468599-1\", \"author\":\"Joshua Bloch\"}")
      .when()
        .post("http://localhost:" + port + "/api/books")
      .then()
        .statusCode(201)
        .extract();

    RestAssured
      .when()
        .get(response.header("Location"))
      .then()
        .statusCode(200)
        .body("id", Matchers.notNullValue())
        .body("isbn", Matchers.equalTo("978-0-13-468599-1"))
        .body("author", Matchers.equalTo("Joshua Bloch"))
        .body("title", Matchers.equalTo("Effective Java"));
  }
}
