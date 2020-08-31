package de.rieckpil.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  private final RandomQuoteClient randomQuoteClient;

  public Application(RandomQuoteClient randomQuoteClient) {
    this.randomQuoteClient = randomQuoteClient;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> System.out.println(randomQuoteClient.getRandomQuote());
  }

}
