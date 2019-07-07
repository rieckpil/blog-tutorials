package de.rieckpil.blog.order.entity;

import javax.json.Json;
import javax.json.JsonObject;

public class Order {

    private Integer orderId;
    private String productName;
    private Integer userId;
    private Integer amount;
    private Double price;

    public Order() {
    }

    public Order(Integer orderId, String productName, Integer userId, Integer amount, Double price) {
        this.orderId = orderId;
        this.productName = productName;
        this.userId = userId;
        this.amount = amount;
        this.price = price;
    }

    public Order(JsonObject jsonOrder) {
        this.productName = jsonOrder.getString("productName", "Default Product");
        this.userId = jsonOrder.getInt("userId", 1);
        this.amount = jsonOrder.getInt("amount", 1);
        this.price = jsonOrder.getJsonNumber("price").doubleValue();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", this.orderId)
                .add("productName", this.productName)
                .add("amount", this.amount)
                .add("price", this.price)
                .build();
    }
}
