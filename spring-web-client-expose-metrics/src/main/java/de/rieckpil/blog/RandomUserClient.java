package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RandomUserClient {

  private final WebClient webClient;

  public RandomUserClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public JsonNode getRandomUserById(int id) {
    return webClient
      .get()
      .uri("https://jsonplaceholder.typicode.com/todos/{id}", id)
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }
}
