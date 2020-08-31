package de.rieckpil.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StartupComponent {

  private final RandomQuoteClient randomQuoteClient;

  public StartupComponent(RandomQuoteClient randomQuoteClient) {
    this.randomQuoteClient = randomQuoteClient;
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> System.out.println(randomQuoteClient.getRandomQuote());
  }
}
