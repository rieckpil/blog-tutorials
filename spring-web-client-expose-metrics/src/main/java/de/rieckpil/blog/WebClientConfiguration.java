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
import reactor.netty.tcp.TcpClient;

@Configuration
public class WebClientConfiguration {

  @Bean
  public WebClient webClient(WebClient.Builder webClientBuilder) {
    TcpClient tcpClient = TcpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
      .doOnConnected(connection ->
        connection.addHandlerLast(new ReadTimeoutHandler(2))
          .addHandlerLast(new WriteTimeoutHandler(2)));

    return webClientBuilder
      .defaultHeader(HttpHeaders.USER_AGENT, "SAMPLE_APP")
      .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
      .build();
  }
}
