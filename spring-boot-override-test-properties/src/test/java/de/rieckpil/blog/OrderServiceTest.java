package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  @Test
  void shouldReturnNoShippingCostsForFreeShippingCountries() {
    OrderService cut = new OrderService(Set.of("US"));

    BigDecimal result = cut.calculateShippingCosts("US");

    assertEquals(BigDecimal.ZERO, result);
  }
}
