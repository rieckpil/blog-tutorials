package de.rieckpil.blog.assertj;

import de.rieckpil.blog.customer.Address;
import de.rieckpil.blog.customer.Customer;
import de.rieckpil.blog.customer.Order;
import de.rieckpil.blog.customer.Product;
import de.rieckpil.blog.registration.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.within;

public class AssertJTest {

  @Test
  void basicAssertions() {

    // make sure to import org.assertj.core.api.Assertions
    Assertions.assertThat("duke").contains("d");

    Assertions.assertThat("duke".toUpperCase()).isEqualTo("DUKE");

    Assertions.assertThat("dUkE".toLowerCase())
      .isEqualTo("duke")
      .hasSize(4)
      .endsWith("e");

    Assertions
      .assertThatThrownBy(() -> {
        throw new RuntimeException("Can't reach database on port 5432");
      })
      .hasMessageContaining("5432")
      .isInstanceOf(RuntimeException.class)
      .hasNoCause();
  }

  @Test
  void customErrorMessage() {
    Assertions.assertThatThrownBy(() -> {
      boolean boardingComplete = false;

      Assertions
        .assertThat(boardingComplete)
        .withFailMessage("Expecting boarding completed when last passenger enters the plane")
        .isTrue();
    });
  }

  @Test
  void softAssertions() {
    Assertions.assertThatThrownBy(() -> {
      SoftAssertions softAssertions = new SoftAssertions();

      softAssertions.assertThat("duke")
        .hasSize(5)
        .isEqualTo("ekud")
        .startsWith("m");

      softAssertions.assertAll();
    });
  }

  @Test
  void advancedAssertions() {

    Assertions
      .assertThat(List.of("duke", "mike", "alice", "john"))
      .containsAnyOf("mike", "duke")
      .hasSizeBetween(3, 5)
      .hasOnlyElementsOfType(String.class);

    Assertions
      .assertThat(List.of(
        new User("duke", LocalDateTime.now().minusMonths(3)),
        new User("alice", LocalDateTime.MIN)))
      .extracting(User::getCreatedAt)
      .filteredOn(createdAt -> createdAt.isBefore(LocalDateTime.now()))
      .hasSize(2);

    Assertions
      .assertThat(List.of("duke", "mike", "anna", "john"))
      .allSatisfy(username -> {
        Assertions.assertThat(username).hasSize(4);
        Assertions.assertThat(username).isLowerCase();
      });

    Assertions
      .assertThat(List.of("duke", "mike", "alice", "john"))
      .anySatisfy(username -> {
        Assertions.assertThat(username).endsWith("n");
        Assertions.assertThat(username).isSubstringOf("johnny");
      });

    Assertions
      .assertThat(LocalDateTime.now())
      .isCloseTo(LocalDateTime.now().plusHours(1), within(2, ChronoUnit.HOURS))
      .isAfter(LocalDateTime.now().minusDays(1))
      .isCloseToUtcNow(within(2, ChronoUnit.HOURS));

    Assertions
      .assertThat(BigDecimal.TEN)
      .isNotNegative()
      .isBetween(BigDecimal.ONE, BigDecimal.valueOf(20.0))
      .isGreaterThanOrEqualTo(BigDecimal.ZERO)
      .isNotZero();
  }

  @Test
  void customAssertions() {

    Customer customer = createTestCustomer("duke42");

    CustomerAssert.assertThat(customer)
      .isVIP()
      .hasOrderVolumeGreaterThan(BigDecimal.TEN);

    Assertions.assertThatThrownBy(() -> CustomerAssert.assertThat(customer)
      .isVIP()
      .hasOrderVolumeGreaterThan(BigDecimal.valueOf(9999.99)));
  }

  private Customer createTestCustomer(String username) {
    Customer customer = new Customer();
    customer.setId(UUID.randomUUID().toString());
    customer.setTags(Set.of("VIP", "PLATINUM_MEMBER", "EARLY_BIRD"));
    customer.setUsername(username);
    customer.setAddress(new Address("Berlin", "Germany", "12347"));
    customer.setOrders(List.of(
      new Order(List.of(
        new Product("MacBook Pro", BigDecimal.valueOf(1499.99), 3L),
        new Product("Kindle Paperwhite", BigDecimal.valueOf(149.00), 10L)
      ), "DEBIT"),
      new Order(List.of(
        new Product("Milk", BigDecimal.valueOf(0.99), 12L),
        new Product("Chocolate", BigDecimal.valueOf(2.99), 42L)
      ), "CREDIT_CARD")));

    return customer;
  }
}
