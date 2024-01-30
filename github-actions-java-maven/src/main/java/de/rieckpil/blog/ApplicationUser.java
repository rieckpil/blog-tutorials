package de.rieckpil.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ApplicationUser {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  public ApplicationUser() {}

  public ApplicationUser(String name) {
    this.name = name;
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
}
