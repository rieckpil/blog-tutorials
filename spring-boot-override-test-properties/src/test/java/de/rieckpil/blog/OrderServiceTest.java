package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

  @Test
  void shouldReturnNoShippingCostsForFreeShippingCountries() {
    OrderService cut = new OrderService(Set.of("US"));

    BigDecimal result = cut.calculateShippingCosts("US");

    assertEquals(BigDecimal.ZERO, result);
  }
}
