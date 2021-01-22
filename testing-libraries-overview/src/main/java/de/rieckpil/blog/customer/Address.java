package de.rieckpil.blog.customer;

public class Address {

  private String city;
  private String country;
  private String postalCode;

  public Address() {
  }

  public Address(String city, String country, String postalCode) {
    this.city = city;
    this.country = country;
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}
