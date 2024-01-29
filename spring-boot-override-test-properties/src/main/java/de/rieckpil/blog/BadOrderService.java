package de.rieckpil.blog;

import java.math.BigDecimal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;

public class BadOrderService {

  @Value("${order.free-shipping-countries}")
  private Set<String> freeShippingCountries;

  public BigDecimal calculateShippingCosts(String countryCode) {
    if (freeShippingCountries.contains(countryCode)) {
      return BigDecimal.ZERO;
    }

    return new BigDecimal("4.99");
  }
}
