package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class RetryClient {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  public RetryClient(WebClient.Builder builder,
                     @Value("${clients.users.url}") String usersBaseUrl, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.webClient = builder.baseUrl(usersBaseUrl).build();
  }

  public JsonNode getData() {
    Mono<ObjectNode> fallback = Mono.just(this.objectMapper.createObjectNode().put("message", "fallback"));

    return this.webClient
      .get()
      .uri("/data")
      .retrieve()
      .bodyToMono(JsonNode.class)
      .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(100)))
      .timeout(Duration.ofSeconds(2),
        fallback)
      .block();
  }
}
