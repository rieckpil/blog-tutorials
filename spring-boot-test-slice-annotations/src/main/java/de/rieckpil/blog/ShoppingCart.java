package de.rieckpil.blog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ShoppingCart {

  @Id
  private String id;
  private List<ShoppingCartItem> shoppingCartItems;

  public ShoppingCart() {
  }

  public ShoppingCart(String id, List<ShoppingCartItem> shoppingCartItems) {
    this.id = id;
    this.shoppingCartItems = shoppingCartItems;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<ShoppingCartItem> getCartItems() {
    return shoppingCartItems;
  }

  public void setCartItems(List<ShoppingCartItem> shoppingCartItems) {
    this.shoppingCartItems = shoppingCartItems;
  }
}
