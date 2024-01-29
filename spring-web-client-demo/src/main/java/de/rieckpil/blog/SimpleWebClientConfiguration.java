package de.rieckpil.blog;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class SimpleWebClientConfiguration {

  private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
  private static final Logger logger = LoggerFactory.getLogger(SimpleApiClient.class);

  // @Bean
  public WebClient webClientFromBuilder(WebClient.Builder webClientBuilder) {

    HttpClient httpClient =
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000) // millis
            .doOnConnected(
                connection ->
                    connection
                        .addHandlerLast(new ReadTimeoutHandler(2)) // seconds
                        .addHandlerLast(new WriteTimeoutHandler(2))); // seconds

    return webClientBuilder
        .baseUrl(BASE_URL)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultCookie("cookieKey", "cookieValue", "teapot", "amsterdam")
        .defaultCookie("secretToken", UUID.randomUUID().toString())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.USER_AGENT, "I'm a teapot")
        .filter(
            ExchangeFilterFunctions.basicAuthentication("rieckpil", UUID.randomUUID().toString()))
        .filter(logRequest())
        .filter(logResponse())
        .build();
  }

  @Bean
  public WebClient webClientFromScratch() {

    HttpClient httpClient =
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000) // millis
            .doOnConnected(
                connection ->
                    connection
                        .addHandlerLast(new ReadTimeoutHandler(2)) // seconds
                        .addHandlerLast(new WriteTimeoutHandler(2))); // seconds

    return WebClient.builder()
        .baseUrl(BASE_URL)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultCookie("cookieKey", "cookieValue", "teapot", "amsterdam")
        .defaultCookie("secretToken", UUID.randomUUID().toString())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.USER_AGENT, "I'm a teapot")
        .filter(
            ExchangeFilterFunctions.basicAuthentication("rieckpil", UUID.randomUUID().toString()))
        .filter(logRequest())
        .filter(logResponse())
        .build();
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
      logger.info("--- Http Headers: ---");
      clientRequest.headers().forEach(this::logHeader);
      logger.info("--- Http Cookies: ---");
      clientRequest.cookies().forEach(this::logHeader);
      return next.exchange(clientRequest);
    };
  }

  private ExchangeFilterFunction authHeader(Function<String, String> token) {
    return (request, next) ->
        next.exchange(
            ClientRequest.from(request)
                .headers(
                    (headers) -> {
                      headers.setBearerAuth(token.apply("xyz"));
                    })
                .build());
  }

  private ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(
        clientResponse -> {
          logger.info("Response: {}", clientResponse.statusCode());
          clientResponse
              .headers()
              .asHttpHeaders()
              .forEach(
                  (name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
          return Mono.just(clientResponse);
        });
  }

  private void logHeader(String name, List<String> values) {
    values.forEach(value -> logger.info("{}={}", name, value));
  }
}
