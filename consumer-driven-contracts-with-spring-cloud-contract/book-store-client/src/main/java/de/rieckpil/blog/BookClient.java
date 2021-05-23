package de.rieckpil.blog;

import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;

@Component
public class BookClient {

  private WebClient webClient;

  @PostConstruct
  public void setUpWebClient() {
    var httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
      .doOnConnected(connection ->
        connection.addHandlerLast(new ReadTimeoutHandler(2))
          .addHandlerLast(new WriteTimeoutHandler(2)));

    this.webClient = WebClient.builder()
      .baseUrl("http://localhost:8080")
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
  }

  public JsonNode getAllAvailableBooks() {

    JsonNode availableBooks = webClient.get()
      .uri("/books")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(JsonNode.class)
      .block();

    return availableBooks;
  }
}
