package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RandomQuoteClient {

  private final WebClient webClient;

  public RandomQuoteClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public JsonNode fetchRandomQuotes() {
    return webClient
      .get()
      .uri("https://quotes.rest/qod")
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }
}
