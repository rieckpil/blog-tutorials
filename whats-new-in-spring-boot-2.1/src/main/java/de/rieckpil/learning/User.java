package de.rieckpil.learning;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

  @Id private Long id;

  private String name;
  private String userId;

  public User() {}

  public User(Long id, String name, String userId) {
    this.id = id;
    this.name = name;
    this.userId = userId;
  }

  public User(String name, String userId) {
    this.name = name;
    this.userId = userId;
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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
