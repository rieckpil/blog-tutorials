package de.rieckpil.blog.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

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
  void advancedRESTAssuredJSONVerification() {
    URI uri = URI.create("http://localhost:" + port + "/api/customers");

    get(uri).then().body("[0].orders.size()", equalTo(2));
    get(uri).then().body("[0].address.city", equalTo("Berlin"));
    get(uri).then().body("[0].orders[0].products[0].name", equalTo("MacBook Pro"));

    List<String> tagList = get(uri).jsonPath().getList("[0].tags");

    assertEquals(3, tagList.size());

    // What is the total order amount for the first customer?
    get(uri)
      .then()
      .body("[0].orders.collect{it.products}.flatten().sum{it.price * it.quantity}", greaterThan(6000.00));

    // Which customer has the most tags?
    get(uri)
      .then()
      .body("max{ it.tags.size() }.username", equalTo("duke42"));

    // Which products where ordered with a quantity >= 42?
    get(uri)
      .then()
      .body("collectMany{c -> c.orders.collect { it.products }}.flatten().findAll{it.quantity >= 42}.name",
        hasItems("Chocolate", "Chewing Gum"));

  }

  @Test
  void basicRESTAssuredXMLExample() {

    given()
      .accept("application/xml")
      .header("X-Custom-Header", "Duke")
      .auth().basic("duke", "secret")
    .when()
      .get(URI.create("http://localhost:" + port + "/api/customers"))
    .then()
      .statusCode(200)
      .header("Content-Type", equalTo("application/xml"))
      .body("List.item[0].username", equalTo("duke42"));

  }

  @Test
  void advancedRESTAssuredXMLVerification() {
    URI uri = URI.create("http://localhost:" + port + "/api/customers");

    List<String> tagList = given().accept("application/xml")
      .get(uri).xmlPath().getList("List.item[0].tags.tag");

    assertEquals(3, tagList.size());

    // Which customers has the least tags?
    given()
      .accept("application/xml")
    .when()
      .get(uri)
    .then()
      .body("List.item.min { it.tags.tag.size() }.username", equalTo("bob"));
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
