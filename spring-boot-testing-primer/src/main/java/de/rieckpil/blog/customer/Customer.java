package de.rieckpil.blog.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private ZonedDateTime joinedAt;

  public Customer() {
  }

  public Customer(String name, ZonedDateTime joinedAt) {
    this.name = name;
    this.joinedAt = joinedAt;
  }

  public Customer(Long id, String name, ZonedDateTime joinedAt) {
    this.id = id;
    this.name = name;
    this.joinedAt = joinedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ZonedDateTime getJoinedAt() {
    return joinedAt;
  }

  public void setJoinedAt(ZonedDateTime joinedAt) {
    this.joinedAt = joinedAt;
  }
}
