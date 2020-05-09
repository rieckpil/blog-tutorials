package de.rieckpil.blog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class FunctionConfiguration {

  private static Log logger = LogFactory.getLog(FunctionConfiguration.class);

  public static void main(String[] args) {
    SpringApplication.run(FunctionConfiguration.class, args);
  }

  @Bean
  public Function<String, String> uppercase() {
    return value -> {
      logger.info("Processing uppercase: " + value);
      return value.toUpperCase();
    };
  }

  public Function<String, String> lowercase() {
    return value -> {
      logger.info("Processing lowercase: " + value);
      return value.toUpperCase();
    };
  }
}
