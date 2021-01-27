package de.rieckpil.blog.customer;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Set;

public class Customer {

  private String username;
  private String id;

  @JacksonXmlElementWrapper(localName = "tags")
  @JacksonXmlProperty(localName = "tag")
  private Set<String> tags;

  @JacksonXmlElementWrapper(localName = "orders")
  @JacksonXmlProperty(localName = "order")
  private List<Order> orders;

  private Address address;

  public Customer() {
  }

  public Customer(String username, String id, Set<String> tags, List<Order> orders, Address address) {
    this.username = username;
    this.id = id;
    this.tags = tags;
    this.orders = orders;
    this.address = address;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
