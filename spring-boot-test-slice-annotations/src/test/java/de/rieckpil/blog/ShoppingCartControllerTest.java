package de.rieckpil.blog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ShoppingCartRepository shoppingCartRepository;

  @Test
  public void shouldReturnAllShoppingCarts() throws Exception {
    when(shoppingCartRepository.findAll()).thenReturn(
      List.of(new ShoppingCart("42",
        List.of(new ShoppingCartItem(
          new Item("MacBook", 999.9), 2)
        ))));

    this.mockMvc.perform(get("/api/carts"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id", Matchers.is("42")))
      .andExpect(jsonPath("$[0].cartItems.length()", Matchers.is(1)))
      .andExpect(jsonPath("$[0].cartItems[0].item.name", Matchers.is("MacBook")))
      .andExpect(jsonPath("$[0].cartItems[0].quantity", Matchers.is(2)));
  }
}
