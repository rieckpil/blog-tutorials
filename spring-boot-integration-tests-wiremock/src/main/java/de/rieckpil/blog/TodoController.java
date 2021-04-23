package de.rieckpil.blog;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final WebClient todoWebClient;

  public TodoController(WebClient todoWebClient) {
    this.todoWebClient = todoWebClient;
  }

  @GetMapping
  public ArrayNode getAllTodos() {
    return this.todoWebClient
      .get()
      .uri("/todos")
      .header("X-Auth", "duke")
      .retrieve()
      .bodyToMono(ArrayNode.class)
      .block();
  }
}
