package de.rieckpil;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CustomerFormObject {

  @NotEmpty
  private String name;

  @NotEmpty
  private String number;

  @Email
  @NotEmpty
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
