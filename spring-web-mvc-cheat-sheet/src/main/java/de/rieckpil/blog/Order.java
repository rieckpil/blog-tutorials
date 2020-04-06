package de.rieckpil.blog;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Set;

@XmlRootElement
public class Order {

  private String id;
  private Set<String> tags;
  private Long customerId;
  private LocalDateTime orderAt;

  public Order() {
  }

  public Order(String id, Set<String> tags, Long customerId, LocalDateTime orderAt) {
    this.id = id;
    this.tags = tags;
    this.customerId = customerId;
    this.orderAt = orderAt;
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

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public LocalDateTime getOrderAt() {
    return orderAt;
  }

  public void setOrderAt(LocalDateTime orderAt) {
    this.orderAt = orderAt;
  }
}
