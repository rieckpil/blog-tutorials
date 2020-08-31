package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class RandomQuoteClient {

  private final RestTemplate restTemplate;

  public RandomQuoteClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .rootUri("https://quotes.rest")
      .setConnectTimeout(Duration.ofSeconds(2))
      .setConnectTimeout(Duration.ofSeconds(2))
      .build();
  }

  public String getRandomQuote() {
    return this.restTemplate
      .getForObject("/qod", JsonNode.class)
      .get("contents")
      .get("quotes").get(0)
      .get("quote").asText();
  }
}
