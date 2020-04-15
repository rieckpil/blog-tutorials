package de.rieckpil.blog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class OrderEvent {

  private String id;
  private String product;
  private String message;
  private boolean expressDelivery;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime orderedAt;

  @JsonCreator
  public OrderEvent(String id, String product, String message, LocalDateTime orderedAt, boolean expressDelivery) {
    this.id = id;
    this.product = product;
    this.message = message;
    this.orderedAt = orderedAt;
    this.expressDelivery = expressDelivery;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getOrderedAt() {
    return orderedAt;
  }

  public void setOrderedAt(LocalDateTime orderedAt) {
    this.orderedAt = orderedAt;
  }

  public boolean isExpressDelivery() {
    return expressDelivery;
  }

  public void setExpressDelivery(boolean expressDelivery) {
    this.expressDelivery = expressDelivery;
  }

  @Override
  public String toString() {
    return "OrderEvent{" +
      "id='" + id + '\'' +
      ", product='" + product + '\'' +
      ", message='" + message + '\'' +
      ", orderedAt=" + orderedAt +
      ", expressDelivery=" + expressDelivery +
      '}';
  }
}
