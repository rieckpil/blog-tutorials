package de.rieckpil.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@SpringBootApplication
public class SpringWebClientExposeMetricsApplication implements CommandLineRunner {

  private final RandomQuoteClient randomQuoteClient;
  private final RandomUserClient randomUserClient;

  public SpringWebClientExposeMetricsApplication(RandomQuoteClient randomQuoteClient, RandomUserClient randomUserClient) {
    this.randomQuoteClient = randomQuoteClient;
    this.randomUserClient = randomUserClient;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringWebClientExposeMetricsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    for (int i = 0; i < 5; i++) {
      try {
        System.out.println(randomQuoteClient.fetchRandomQuotes());
        System.out.println(randomUserClient.getRandomUserById(i));
      } catch (WebClientResponseException ex) {
        System.out.println(ex.getRawStatusCode());
      }
    }
  }
}
