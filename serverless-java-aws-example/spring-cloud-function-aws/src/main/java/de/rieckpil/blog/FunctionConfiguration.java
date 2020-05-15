package de.rieckpil.blog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class FunctionConfiguration {

  private static Log logger = LogFactory.getLog(FunctionConfiguration.class);

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(FunctionConfiguration.class, args);

    FunctionCatalog functionCatalog = context.getBean(FunctionCatalog.class);
    Function<String, String> lowercaseFunction = functionCatalog.lookup("lowercase");
    Function<String, String> uppercaseFunction = functionCatalog.lookup("uppercase");
    System.out.println(lowercaseFunction.apply("DUKE"));
    System.out.println(uppercaseFunction.apply("duke"));
  }

  @Bean
  public Function<String, String> uppercase() {
    return value -> {
      logger.info("Processing uppercase: " + value);
      return value.toUpperCase();
    };
  }

  @Bean
  public Function<String, String> lowercase() {
    return value -> {
      logger.info("Processing lowercase: " + value);
      return value.toLowerCase();
    };
  }
}
