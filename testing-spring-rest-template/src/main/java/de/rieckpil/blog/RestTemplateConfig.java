package de.rieckpil.blog;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate reqresRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.rootUri("https://reqres.in/")
      .setConnectTimeout(Duration.ofSeconds(2))
      .setReadTimeout(Duration.ofSeconds(2))
      .build();
  }
}
