package de.rieckpil.blog;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserAgentCustomizer implements WebClientCustomizer {

  @Override
  public void customize(WebClient.Builder webClientBuilder) {
    webClientBuilder.defaultHeader("User-Agent", "MY-APPLICATION");
  }
}
