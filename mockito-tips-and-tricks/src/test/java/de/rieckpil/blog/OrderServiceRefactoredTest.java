package de.rieckpil.blog;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceRefactoredTest {

  private OrderIdGenerator orderIdGenerator = mock(OrderIdGenerator.class);
  private Clock clock = mock(Clock.class);
  private OrderServiceRefactored cut = new OrderServiceRefactored(clock, orderIdGenerator);

  @Test
  void shouldIncludeRandomOrderIdWhenNoParentOrderExists() {
    when(orderIdGenerator.generateOrderId()).thenReturn("8d8b30e3-de52-4f1c-a71c-9905a8043dac");

    Order result = cut.createOrder("MacBook Pro", 2L, null);

    assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.getId());
  }

  @Test
  void shouldIncludeCurrentTimeWhenCreatingANewOrder() {
    LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020, 1, 1, 12, 0);
    Clock fixedClock = Clock.fixed(defaultLocalDateTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    when(clock.instant()).thenReturn(fixedClock.instant());
    when(clock.getZone()).thenReturn(fixedClock.getZone());

    Order result = cut.createOrder("MacBook Pro", 2L, "42");

    assertEquals(defaultLocalDateTime, result.getCreationDate());
  }
}
