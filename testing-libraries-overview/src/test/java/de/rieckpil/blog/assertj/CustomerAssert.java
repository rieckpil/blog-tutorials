package de.rieckpil.blog.assertj;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.Order;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;
import java.util.List;

public class CustomerAssert extends AbstractAssert<CustomerAssert, Customer> {

  protected CustomerAssert(Customer customer) {
    super(customer, CustomerAssert.class);
  }

  public static CustomerAssert assertThat(Customer actual) {
    return new CustomerAssert(actual);
  }

  public CustomerAssert isVIP() {
    isNotNull();

    if (!this.actual.getTags().contains("VIP")) {
      failWithMessage("Expected customer <%s> to be VIP but not VIP tag was found <%s>", actual.getUsername(), actual.getTags());
    }

    return this;
  }

  public CustomerAssert hasOrderVolumeGreaterThan(BigDecimal expected) {
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
