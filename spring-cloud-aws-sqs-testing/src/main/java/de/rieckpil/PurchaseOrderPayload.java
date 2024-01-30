package de.rieckpil;

public class PurchaseOrderPayload {

  private String customerName;
  private Long orderAmount;

  public PurchaseOrderPayload() {}

  public PurchaseOrderPayload(String customerName, Long orderAmount) {
    this.customerName = customerName;
    this.orderAmount = orderAmount;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public Long getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(Long orderAmount) {
    this.orderAmount = orderAmount;
  }
}
