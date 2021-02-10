package de.rieckpil.blog.hamcrest;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.Order;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;
import java.util.List;

public class IsVIP extends AbstractAssert<IsVIP, Customer> {

  protected IsVIP(Customer customer) {
    super(customer, IsVIP.class);
  }

  public static IsVIP assertThat(Customer actual) {
    return new IsVIP(actual);
  }

  public IsVIP isVIP() {
    isNotNull();

    if (!this.actual.getTags().contains("VIP")) {
      failWithMessage("Expected customer <%s> to be VIP but not VIP tag was found <%s>", actual.getUsername(), actual.getTags());
    }

    return this;
  }

  public IsVIP hasOrderVolumeGreaterThan(BigDecimal expected) {
    isNotNull();

    BigDecimal orderVolume = this.actual
      .getOrders()
      .stream()
      .map(Order::getProducts)
      .flatMap(List::stream)
      .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (expected.compareTo(orderVolume) > 0) {
      failWithMessage("Expected customer's order volume to be greater than <%s> but was <%s>", expected, orderVolume);
    }

    return this;
  }
}
