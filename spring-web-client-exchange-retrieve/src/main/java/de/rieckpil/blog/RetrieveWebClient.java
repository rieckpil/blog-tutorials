package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RetrieveWebClient {

  private final WebClient jsonPlaceholderWebClient;

  public RetrieveWebClient(WebClient jsonPlaceholderWebClient) {
    this.jsonPlaceholderWebClient = jsonPlaceholderWebClient;
  }

  public JsonNode getTodo(String id) {
    return this.jsonPlaceholderWebClient.get()
      .uri("/todos/{id}", id)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.rawStatusCode() == 418 ? Mono.empty() : Mono.error(new RuntimeException("Error")))
      .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("Error")))
      .bodyToMono(JsonNode.class)
      .block();
  }

  public JsonNode getTodos() {
    return this.jsonPlaceholderWebClient.get()
      .uri("/todos")
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }

  public boolean createTodo(JsonNode payload) {
    ResponseEntity<JsonNode> response = this.jsonPlaceholderWebClient
      .post()
      .uri("/todos")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(payload)
      .retrieve()
      .toEntity(JsonNode.class)
      .block();

    response.getHeaders().forEach((key, value) -> System.out.println(key + ":" + value));

    if (response.getStatusCodeValue() == 201) {
      return !response.getHeaders().get(HttpHeaders.LOCATION).isEmpty();
    } else {
      return false;
    }
  }
}
