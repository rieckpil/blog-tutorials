package de.rieckpil.blog;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {

  private String id;
  private Long customerId;
  private String product;
  private Long amount;
  private boolean processed;

  public Order() {
  }

  public Order(String id, Long customerId, String product, Long amount, boolean processed) {
    this.id = id;
    this.customerId = customerId;
    this.product = product;
    this.amount = amount;
    this.processed = processed;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public boolean isProcessed() {
    return processed;
  }

  public void setProcessed(boolean processed) {
    this.processed = processed;
  }

  @Override
  public String toString() {
    return "Order{" +
      "id='" + id + '\'' +
      ", customerId=" + customerId +
      ", product='" + product + '\'' +
      ", amount=" + amount +
      ", processed=" + processed +
      '}';
  }
}
