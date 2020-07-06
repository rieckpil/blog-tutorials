package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ExchangeWebClient {

  private final WebClient jsonPlaceholderWebClient;

  public ExchangeWebClient(WebClient jsonPlaceholderWebClient) {
    this.jsonPlaceholderWebClient = jsonPlaceholderWebClient;
  }

  public JsonNode getTodo(String id) {
    return this.jsonPlaceholderWebClient.get()
      .uri("/todos/{id}", id)
      .exchange()
      .flatMap(clientResponse -> clientResponse.bodyToMono(JsonNode.class))
      .block();
  }

  public JsonNode getTodos() {
    return this.jsonPlaceholderWebClient.get()
      .uri("/todos")
      .exchange()
      .flatMap(clientResponse -> clientResponse.bodyToMono(JsonNode.class))
      .block();
  }

  public boolean createTodo(JsonNode payload) {
    ClientResponse response = this.jsonPlaceholderWebClient
      .post()
      .uri("/todos")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(payload)
      .exchange()
      .doOnSuccess(clientResponse ->
        System.out.println("Location header: " + clientResponse.headers().header(HttpHeaders.LOCATION)))
      .block();

    if (response.statusCode().value() == 201) {
      return !response.headers().header(HttpHeaders.LOCATION).isEmpty();
    } else {
      return false;
    }
  }

}
