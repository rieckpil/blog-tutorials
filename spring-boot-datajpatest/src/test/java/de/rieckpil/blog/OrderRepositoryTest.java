package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer("postgres:12")
    .withDatabaseName("springboot")
    .withPassword("springboot")
    .withUsername("springboot");

  @DynamicPropertySource
  static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
    propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
    propertyRegistry.add("spring.datasource.password", database::getPassword);
    propertyRegistry.add("spring.datasource.username", database::getUsername);
  }

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void shouldReturnOrdersThatContainMacBookPro() {

    orderRepository.save(createOrder("42", """
         [{"name": "MacBook Pro", "amount" : 42}, {"name": "iPhone Pro", "amount" : 42}]
      """));

    orderRepository.save(createOrder("43", """
         [{"name": "Kindle", "amount" : 13}, {"name": "MacBook Pro", "amount" : 10}]
      """));

    orderRepository.save(createOrder("44", "[]"));

    List<Order> orders = orderRepository.findAllContainingMacBookPro();

    assertEquals(2, orders.size());
  }

  private Order createOrder(String trackingNumber, String items) {
    Order order = new Order();
    order.setTrackingNumber(trackingNumber);
    order.setItems(items);
    return order;
  }
}
