package de.rieckpil.blog.hamcrest;

import de.rieckpil.blog.customer.Customer;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsVIP extends TypeSafeMatcher<Customer> {

  @Override
  protected boolean matchesSafely(Customer customer) {
    return customer.getTags().contains("VIP");
  }

  @Override
  protected void describeMismatchSafely(Customer item, Description mismatchDescription) {
    mismatchDescription.appendText("customer only had the following tags ");
    mismatchDescription.appendValue(item.getTags());
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("customer to be VIP");
  }

  public static Matcher isVIP() {
    return new IsVIP();
  }
}
