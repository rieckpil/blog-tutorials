package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SimpleApiClient {

    @Autowired
    private WebClient defaultWebClient;

    public JsonNode getTodoFromAPI() {
        return this.defaultWebClient.get().uri("/todos/1")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx eror");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx eror");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(JsonNode.class)
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
