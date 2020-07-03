package de.rieckpil.blog;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;

@Configuration
public class WebClientConfig {

  private static final int TIMEOUT_IN_SECONDS = 2;

  @Bean
  public WebClient jsonPlaceholderWebClient(WebClient.Builder webClientBuilder) {
    TcpClient tcpClient = TcpClient.create()
      .option(CONNECT_TIMEOUT_MILLIS, TIMEOUT_IN_SECONDS * 1000)
      .doOnConnected(connection ->
        connection
          .addHandlerLast(new ReadTimeoutHandler(TIMEOUT_IN_SECONDS))
          .addHandlerLast(new WriteTimeoutHandler(TIMEOUT_IN_SECONDS)));

    return webClientBuilder
      .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
      .baseUrl("https://jsonplaceholder.typicode.com/")
      .build();
  }
}
