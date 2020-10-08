package de.rieckpil.blog.staticsmocks;

import java.time.Clock;
import java.time.LocalDateTime;

public class OrderServiceRefactored {

  private final Clock clock;
  private final OrderIdGenerator orderIdGenerator;

  public OrderServiceRefactored(Clock clock, OrderIdGenerator orderIdGenerator) {
    this.clock = clock;
    this.orderIdGenerator = orderIdGenerator;
  }

  public Order createOrder(String productName, Long amount, String parentOrderId) {
    Order order = new Order();
    order.setId(parentOrderId == null ? orderIdGenerator.generateOrderId() : parentOrderId);
    order.setCreationDate(LocalDateTime.now(clock));
    order.setAmount(amount);
    order.setProductName(productName);

    return order;
  }
}
