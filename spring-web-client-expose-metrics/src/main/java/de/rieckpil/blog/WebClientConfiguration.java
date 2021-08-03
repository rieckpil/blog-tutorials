package de.rieckpil.blog;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

  @Bean
  public WebClient webClient(WebClient.Builder webClientBuilder) {
    HttpClient httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 4_000)
      .doOnConnected(connection ->
        connection.addHandlerLast(new ReadTimeoutHandler(4))
          .addHandlerLast(new WriteTimeoutHandler(4)));

    return webClientBuilder
      .defaultHeader(HttpHeaders.USER_AGENT, "SAMPLE_APP")
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
  }
}
