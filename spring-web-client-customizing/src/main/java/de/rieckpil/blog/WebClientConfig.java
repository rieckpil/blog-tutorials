package de.rieckpil.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient stockApiClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder.baseUrl("https://stock.api").build();
  }

  @Bean
  public WebClient randomApiClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder.baseUrl("https://random.api").build();
  }
}
