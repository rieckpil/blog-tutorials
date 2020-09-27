package de.rieckpil.blog.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {

  @NotEmpty
  private String username;

  @Email
  private String email;

  public User() {
  }

  public User(String username, String email) {
    this.username = username;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
