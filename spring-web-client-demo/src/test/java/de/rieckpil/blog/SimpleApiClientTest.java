package de.rieckpil.blog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleApiClientTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @Disabled
  public void testGetTodosAPICall() {
    this.webTestClient
      .get()
      .uri("https://jsonplaceholder.typicode.com/todos/1")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
      .expectBody()
      .jsonPath("$.title").isNotEmpty()
      .jsonPath("$.userId").isNotEmpty()
      .jsonPath("$.completed").isNotEmpty();
  }

  @Test
  @Disabled
  public void testPostNewTodoCall() {
    this.webTestClient
      .post()
      .uri("https://jsonplaceholder.typicode.com/todos")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": \"1\"}"))
      .exchange()
      .expectStatus().isCreated()
      .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
      .expectBody()
      .jsonPath("$.title").isNotEmpty()
      .jsonPath("$.body").isNotEmpty()
      .jsonPath("$.userId").isNotEmpty()
      .jsonPath("$.userId").isEqualTo("1")
      .jsonPath("$.id").isNotEmpty();
  }

}
