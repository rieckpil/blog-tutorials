package de.rieckpil.blog.customer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

  @JacksonXmlElementWrapper(localName = "products")
  @JacksonXmlProperty(localName = "product")
  private List<Product> products;

  private String paymentMethod;

  public Order() {
  }

  public Order(List<Product> products, String paymentMethod) {
    this.products = products;
    this.paymentMethod = paymentMethod;
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

}
