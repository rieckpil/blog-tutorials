package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadOrderServiceTest {

  @Test
  void avoidThis() {
    BadOrderService cut = new BadOrderService();

    // you should avoid this and favor constructor injection
    ReflectionTestUtils.setField(cut, "freeShippingCountries", Set.of("US"));

    BigDecimal result = cut.calculateShippingCosts("US");

    assertEquals(BigDecimal.ZERO, result);
  }
}
