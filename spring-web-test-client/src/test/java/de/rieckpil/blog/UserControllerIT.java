package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldReturnThreeDefaultUsers() {
    this.webTestClient
      .get()
      .uri("/api/users")
      .header(ACCEPT, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus()
      .is2xxSuccessful()
      .expectHeader()
      .contentType(APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.length()").isEqualTo(3)
      .jsonPath("$[0].id").isEqualTo(1)
      .jsonPath("$[0].name").isEqualTo("duke")
      .jsonPath("$[0].tags").isNotEmpty();
  }

  @Test
  public void shouldNotSupportMediaTypeXML() {
    this.webTestClient
      .get()
      .uri("/api/users")
      .header(ACCEPT, APPLICATION_XML_VALUE)
      .exchange()
      .expectStatus()
      .isEqualTo(NOT_ACCEPTABLE);
  }

@Test
public void shouldCreateNewUser() {

  var newUser = new User(10L, "test", Set.of("testing", "webtestclient"));

  var userCreationResponse = this.webTestClient
    .post()
    .uri("/api/users")
    .body(Mono.just(newUser), User.class)
    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
    .header(ACCEPT, APPLICATION_JSON_VALUE)
    .exchange()
    .expectStatus()
    .isEqualTo(CREATED)
    .returnResult(Void.class);

  var locationUrlOfNewUser = userCreationResponse.getResponseHeaders().get(LOCATION).get(0);

  this.webTestClient
    .get()
    .uri(locationUrlOfNewUser)
    .header(ACCEPT, APPLICATION_JSON_VALUE)
    .exchange()
    .expectStatus()
    .isEqualTo(OK)
    .expectBody(User.class)
    .isEqualTo(newUser);

  this.webTestClient
    .delete()
    .uri("/api/users/{id}", newUser.getId())
    .exchange();
}

  @Test
  public void shouldReturnNotFoundForUnknownUserId() {
    this.webTestClient
      .get()
      .uri("/api/users/{id}", 42)
      .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus()
      .isEqualTo(NOT_FOUND);
  }
}
