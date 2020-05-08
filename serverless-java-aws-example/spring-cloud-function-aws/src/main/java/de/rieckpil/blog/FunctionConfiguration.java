package de.rieckpil.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class FunctionConfiguration {

  public static void main(String[] args) {
    SpringApplication.run(FunctionConfiguration.class, args);
  }

  @Bean
  public Function<String, String> uppercase() {
    return value -> value.toUpperCase();
  }

  @Bean
  public Function<String, String> lowercase() {
    return value -> value.toLowerCase();
  }

}
