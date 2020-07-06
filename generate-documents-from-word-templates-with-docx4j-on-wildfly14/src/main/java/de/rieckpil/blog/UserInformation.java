package de.rieckpil.blog;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserInformation {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  @Size(max = 500)
  private String message;

  @NotEmpty
  private String salutation;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSalutation() {
    return salutation;
  }

  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  @Override
  public String toString() {
    return "UserInformation{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", message='" + message + '\'' +
      ", salutation='" + salutation + '\'' +
      '}';
  }
}
