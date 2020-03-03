package de.rieckpil.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ResponseLoggingCustomizer implements WebClientCustomizer {

  private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingCustomizer.class);

  @Override
  public void customize(WebClient.Builder webClientBuilder) {
    webClientBuilder.filter(logResponse());
    webClientBuilder.filter(logRequest());
  }

  private ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
      logger.info("Response: {}", clientResponse.statusCode());
      logger.info("--- Http Headers of Response: ---");
      clientResponse.headers().asHttpHeaders()
        .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
      return Mono.just(clientResponse);
    });
  }

  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
      logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
      logger.info("--- Http Headers of Request: ---");
      clientRequest.headers().forEach(this::logHeader);
      return next.exchange(clientRequest);
    };
  }

  private void logHeader(String name, List<String> values) {
    values.forEach(value -> logger.info("{}={}", name, value));
  }
}
