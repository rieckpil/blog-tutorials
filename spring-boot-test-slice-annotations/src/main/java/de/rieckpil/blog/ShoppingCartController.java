package de.rieckpil.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class ShoppingCartController {

  private final ShoppingCartRepository shoppingCartRepository;

  public ShoppingCartController(ShoppingCartRepository shoppingCartRepository) {
    this.shoppingCartRepository = shoppingCartRepository;
  }

  @GetMapping
  private Iterable<ShoppingCart> getAllShoppingCarts() {
    return shoppingCartRepository.findAll();
  }
}
