package de.rieckpil.blog;

import java.util.List;

public class User {

  private String firstName;
  private String lastName;
  private Address address;
  private List<Hobby> hobbies;

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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Hobby> getHobbies() {
    return hobbies;
  }

  public void setHobbies(List<Hobby> hobbies) {
    this.hobbies = hobbies;
  }

  @Override
  public String toString() {
    return "User{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", address=" + address +
      ", hobbies=" + hobbies +
      '}';
  }
}
