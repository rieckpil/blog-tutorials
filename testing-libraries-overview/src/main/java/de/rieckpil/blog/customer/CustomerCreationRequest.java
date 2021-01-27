package de.rieckpil.blog.customer;

public class CustomerCreationRequest {

  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "CustomerCreationRequest{" +
      "username='" + username + '\'' +
      '}';
  }
}
