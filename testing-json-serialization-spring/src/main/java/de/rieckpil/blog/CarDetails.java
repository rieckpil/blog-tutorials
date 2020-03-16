package de.rieckpil.blog;

public class CarDetails {
  private String manufacturer;
  private String type;
  private String color;

  public CarDetails(String manufacturer, String type, String color) {
    this.manufacturer = manufacturer;
    this.type = type;
    this.color = color;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
