package de.rieckpil.blog.hamcrest;

import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.Order;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;
import java.util.List;

public class HasOrderVolumeGreaterThan extends TypeSafeMatcher<Customer> {

  private BigDecimal expected;

  public HasOrderVolumeGreaterThan(BigDecimal expected) {
    this.expected = expected;
  }

  @Override
  protected boolean matchesSafely(Customer customer) {
    BigDecimal actualOrderVolume = calculateOrderVolume(customer.getOrders());
    return expected.compareTo(actualOrderVolume) < 0;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("customer to have a greater order volume than ");
    description.appendValue(expected);
  }

  @Override
  protected void describeMismatchSafely(Customer item, Description mismatchDescription) {
    mismatchDescription.appendText("customer only ordered for a total of ");
    mismatchDescription.appendValue(calculateOrderVolume(item.getOrders()));
  }

  public static Matcher hasOrderVolumeGreaterThan(BigDecimal expected) {
    return new HasOrderVolumeGreaterThan(expected);
  }

  private BigDecimal calculateOrderVolume(List<Order> orders) {
    return orders
      .stream()
      .map(Order::getProducts)
      .flatMap(List::stream)
      .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
