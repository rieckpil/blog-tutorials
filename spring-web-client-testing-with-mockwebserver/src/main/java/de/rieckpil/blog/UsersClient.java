package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsersClient {

  private final WebClient webClient;

  public UsersClient(WebClient.Builder builder,
                     @Value("${clients.users.url}") String usersBaseUrl) {
    this.webClient = builder.baseUrl(usersBaseUrl).build();
  }

  public JsonNode getUserById(Long id) {
    return this.webClient
      .get()
      .uri("/users/{id}", id)
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();
  }

  public JsonNode createNewUser(JsonNode payload) {
    ClientResponse clientResponse = this.webClient
      .post()
      .uri("/users")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .bodyValue(payload)
      .exchange()
      .block();

    if (clientResponse.statusCode().equals(HttpStatus.CREATED)) {
      return clientResponse.bodyToMono(JsonNode.class).block();
    } else {
      throw new RuntimeException("Unable to create new user!");
    }
  }
}
