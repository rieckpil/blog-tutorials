package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/random")
public class RandomDataController {

  private final WebClient webClient;

  public RandomDataController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping
  public JsonNode getRandomData() {
    return webClient
      .get()
      .uri("https://jsonplaceholder.typicode.com/todos")
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }
}
