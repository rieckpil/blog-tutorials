package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class SimpleApiClient {

  private final WebClient defaultWebClient;
  private final ObjectMapper objectMapper;

  public SimpleApiClient(WebClient defaultWebClient, ObjectMapper objectMapper) {
    this.defaultWebClient = defaultWebClient;
    this.objectMapper = objectMapper;
  }

  public JsonNode getTodoFromAPI() {
    return this.defaultWebClient.get().uri("/todos/1")
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> {
        System.out.println("4xx error");
        return Mono.error(new RuntimeException("4xx"));
      })
      .onStatus(HttpStatus::is5xxServerError, response -> {
        System.out.println("5xx error");
        return Mono.error(new RuntimeException("5xx"));
      })
      .bodyToMono(JsonNode.class)
      .block();
  }

  public JsonNode getRetryTodoFromAPI() {
    return this.defaultWebClient.get().uri("/todos/1")
      .retrieve()
      .bodyToMono(JsonNode.class)
      .retryWhen(Retry.max(5))
      .timeout(Duration.ofSeconds(2), Mono.just(objectMapper.createObjectNode().put("message", "fallback")))
      .block();
  }

  public JsonNode postToTodoAPI() {
    return this.defaultWebClient
      .post()
      .uri("/todos")
      .body(BodyInserters.fromValue("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": \"1\"}"))
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }
}
