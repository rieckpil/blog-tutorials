package de.rieckpil.blog.customer;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

  private List<Product> products;
  private String paymentMethod;
  private LocalDateTime orderedAt;

  public Order() {
  }

  public Order(List<Product> products, String paymentMethod, LocalDateTime orderedAt) {
    this.products = products;
    this.paymentMethod = paymentMethod;
    this.orderedAt = orderedAt;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }
}
