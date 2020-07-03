package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final ExchangeWebClient exchangeWebClient;
  private final RetrieveWebClient retrieveWebClient;
  private final ObjectMapper objectMapper;

  public Application(ExchangeWebClient exchangeWebClient, RetrieveWebClient retrieveWebClient, ObjectMapper objectMapper) {
    this.exchangeWebClient = exchangeWebClient;
    this.retrieveWebClient = retrieveWebClient;
    this.objectMapper = objectMapper;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(retrieveWebClient.getTodos());
    System.out.println(exchangeWebClient.getTodos());

    System.out.println(exchangeWebClient.createTodo(objectMapper
      .createObjectNode()
      .put("task", "learn Spring Boot")));

    System.out.println(retrieveWebClient.createTodo(objectMapper
      .createObjectNode()
      .put("task", "learn Spring Boot")));
  }
}
