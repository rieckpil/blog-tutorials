package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.rieckpil.blog.initializer.WireMockInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = WireMockInitializer.class)
class ApplicationInitializerTest {

  @Value("${clients.order-api.base-url}")
  private String orderApiBaseUrl;

  @Test
  void contextLoads() {
    System.out.println(orderApiBaseUrl);
    assertNotNull(orderApiBaseUrl);
  }
}
