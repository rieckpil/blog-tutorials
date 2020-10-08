package de.rieckpil.blog.staticsmocks;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {

  public Order createOrder(String productName, Long amount, String parentOrderId) {
    Order order = new Order();

    order.setId(parentOrderId == null ? UUID.randomUUID().toString() : parentOrderId);
    order.setCreationDate(LocalDateTime.now());
    order.setAmount(amount);
    order.setProductName(productName);

    return order;
  }
}
