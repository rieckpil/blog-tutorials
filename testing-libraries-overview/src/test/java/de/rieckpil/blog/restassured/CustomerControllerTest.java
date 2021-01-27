package de.rieckpil.blog.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

  @LocalServerPort
  private Integer port;

  @Test
  void basicRESTAssuredJSONExample() {
    given()
      .accept("application/json")
      .header("X-Custom-Header", "Duke")
      .auth().basic("duke", "secret")
    .when()
      .get(URI.create("http://localhost:" + port + "/api/customers"))
    .then()
      .statusCode(200)
      .header("Content-Type", equalTo("application/json"))
      .body("[0].username", equalTo("duke42"));
  }

  @Test
  @Disabled
  void basicRESTAssuredXMLExample() {

    given()
      .accept("application/xml")
      .header("X-Custom-Header", "Duke")
      .auth().basic("duke", "secret")
    .when()
      .get(URI.create("http://localhost:" + port + "/api/customers"))
    .then()
      .body("[0].username", equalTo("duke42"));

  }

  @Test
  void requestAndResponseSpecificationReuse() {

    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecBuilder.setContentType("application/json");
    requestSpecBuilder.setBaseUri("http://localhost");
    requestSpecBuilder.setPort(port);
    requestSpecBuilder.setBody("{\"username\": \"duke\"}");
    requestSpecBuilder.addHeader("X-Important", "Duke");
    RequestSpecification entityCreationRequest = requestSpecBuilder.build();

    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
    responseSpecBuilder.expectStatusCode(201);
    responseSpecBuilder.expectHeader("Location", not(emptyString()));
    ResponseSpecification entityCreationSpec = responseSpecBuilder.build();

    given()
      .spec(entityCreationRequest)
      .header("X-Super-Important", "secret")
    .when()
      .post("/api/customers")
    .then()
      .spec(entityCreationSpec)
      .body(emptyString());

  }
}
