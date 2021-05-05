package de.rieckpil.blog.deepstubs;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

public class InspirationalQuotesClient {

  private final WebClient webClient;

  public InspirationalQuotesClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public String fetchRandomQuote() {
    try {
      return this.webClient
        .get()
        .uri("/api/quotes")
        .retrieve()
        .bodyToMono(String.class)
        .block();
    } catch (WebClientException webClientException) {
      return "Every time a mock returns a mock, a fairy dies.";
    }
  }
}
