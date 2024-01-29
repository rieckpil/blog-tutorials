package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
    properties = {
      "spring.test.database.replace=NONE",
      "spring.datasource.url=jdbc:tc:postgresql:12:///springboot"
    })
class OrderRepositoryShortTest {

  @Autowired private OrderRepository orderRepository;

  @Test
  @Sql("/scripts/INIT_THREE_ORDERS.sql")
  void shouldReturnOrdersThatContainMacBookPro() {
    List<Order> orders = orderRepository.findAllContainingMacBookPro();
    assertEquals(2, orders.size());
  }
}
