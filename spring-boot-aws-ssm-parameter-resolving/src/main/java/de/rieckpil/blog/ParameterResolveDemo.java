package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ParameterResolveDemo implements CommandLineRunner {

  @Value("${message}")
  private String message;

  @Value("${spring.datasource.password}")
  private String dataSourcePassword;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Message property: " + message);
    System.out.println("DataSource password property: " + dataSourcePassword);
  }
}
