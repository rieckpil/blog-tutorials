package de.rieckpil.blog.staticsmocks;

import java.util.UUID;

public class OrderIdGenerator {
  public String generateOrderId() {
    return UUID.randomUUID().toString();
  }
}
