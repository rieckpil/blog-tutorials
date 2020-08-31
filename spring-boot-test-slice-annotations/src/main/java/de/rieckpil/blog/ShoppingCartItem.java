package de.rieckpil.blog;

class ShoppingCartItem {

  private Item item;
  private int quantity;

  public ShoppingCartItem() {
  }

  public ShoppingCartItem(Item item) {
    this.item = item;
    this.quantity = 1;
  }

  public void increment() {
    this.quantity++;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "ShoppingCartItem{" +
      "item=" + item +
      ", quantity=" + quantity +
      '}';
  }
}
