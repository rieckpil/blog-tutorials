package de.rieckpil.blog;

import java.math.BigDecimal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;

public class OrderService {

  private final Set<String> freeShippingCountries;

  public OrderService(
      @Value("${order.free-shipping-countries}") Set<String> freeShippingCountries) {
    this.freeShippingCountries = freeShippingCountries;
  }

  public BigDecimal calculateShippingCosts(String countryCode) {
    if (freeShippingCountries.contains(countryCode)) {
      return BigDecimal.ZERO;
    }

    return new BigDecimal("4.99");
  }
}
