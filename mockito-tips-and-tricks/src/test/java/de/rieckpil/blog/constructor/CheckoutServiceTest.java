package de.rieckpil.blog.constructor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CheckoutServiceTest {

  @Test
  void mockObjectConstruction() {
    try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class,
      (mock, context) -> {
        // further stubbings ...
        when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
      })) {

      CheckoutService cut = new CheckoutService();

      BigDecimal result = cut.purchaseProduct("MacBook Pro", "42");

      assertEquals(BigDecimal.TEN, result);
      assertEquals(1, mocked.constructed().size());
    }
  }
}
