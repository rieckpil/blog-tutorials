package de.rieckpil.blog.staticmocks;

import de.rieckpil.blog.staticsmocks.Order;
import de.rieckpil.blog.staticsmocks.OrderIdGenerator;
import de.rieckpil.blog.staticsmocks.OrderServiceRefactored;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderServiceRefactoredTest {

  private final OrderIdGenerator orderIdGenerator = mock(OrderIdGenerator.class);
  private final Clock clock = mock(Clock.class);
  private final OrderServiceRefactored cut = new OrderServiceRefactored(clock, orderIdGenerator);

  @Test
  void shouldIncludeRandomIdAndCurrentDateTime() {
    when(orderIdGenerator.generateOrderId()).thenReturn("8d8b30e3-de52-4f1c-a71c-9905a8043dac");

    LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020, 1, 1, 12, 0);
    Clock fixedClock = Clock.fixed(defaultLocalDateTime.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    when(clock.instant()).thenReturn(fixedClock.instant());
    when(clock.getZone()).thenReturn(fixedClock.getZone());

    Order result = cut.createOrder("MacBook Pro", 2L, null);

    assertEquals("8d8b30e3-de52-4f1c-a71c-9905a8043dac", result.getId());
    assertEquals(defaultLocalDateTime, result.getCreationDate());
  }
}
