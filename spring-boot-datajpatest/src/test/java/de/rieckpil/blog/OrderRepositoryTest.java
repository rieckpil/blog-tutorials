package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  void shouldNotBeNull() {
    assertNotNull(orderRepository);
  }

  @Test
  void shouldReturnOrders() {

    Order order = new Order();
    order.setTrackingNumber(UUID.randomUUID().toString());
    order.setItems("""
       [
        {
          "name": "MacBook Pro",
          "amount" : 42
        }
       ]
      """);

    Order result = testEntityManager.persistFlushFind(order);

    List<Order> orders = orderRepository.findAllContainingMacBookPro();

    System.out.println(orders);
  }

}
