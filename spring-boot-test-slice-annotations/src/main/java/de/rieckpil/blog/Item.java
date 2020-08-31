package de.rieckpil.blog;

import org.springframework.data.annotation.Id;

public class Item {

  @Id
  private String id;
  private String name;
  private double price;

  private Item() {
  }

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Item{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", price=" + price +
      '}';
  }
}
