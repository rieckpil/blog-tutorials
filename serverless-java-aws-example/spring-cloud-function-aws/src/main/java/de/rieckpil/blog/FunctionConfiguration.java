package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.time.LocalDate;
import java.util.function.Function;

@SpringBootApplication
public class FunctionConfiguration {

  private final ObjectMapper objectMapper;

  private static Log logger = LogFactory.getLog(FunctionConfiguration.class);

  public FunctionConfiguration(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(FunctionConfiguration.class, args);
  }

  @Bean
  public Function<String, String> uppercaseFunction() {
    return value -> {
      logger.info("Processing uppercase: " + value);
      return value.toUpperCase();
    };
  }

  @Bean
  public Function<Message<Person>, Message<Person>> processPerson() {
    return value -> {
      logger.info("Processing incoming request: " + value.getPayload());
      logger.info("Processing incoming request: " + value.getHeaders());
      return new GenericMessage<>(new Person("Duke", LocalDate.now()));
    };
  }
}
