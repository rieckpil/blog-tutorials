package de.rieckpil.blog

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

  @Bean
  fun jsonPlaceHolderWebClient(builder: WebClient.Builder) = builder
    .clone()
    .baseUrl("https://jsonplaceholder.typicode.com")
    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
    .defaultHeader(HttpHeaders.USER_AGENT, "SpringBootKotlinApplication")
    .build()
}
