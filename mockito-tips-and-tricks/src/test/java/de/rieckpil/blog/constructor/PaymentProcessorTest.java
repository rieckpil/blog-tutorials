package de.rieckpil.blog.constructor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PaymentProcessorTest {

  @Test
  void mockObjectConstruction() {

    // a real object of the PaymentProcessor is returned
    System.out.println(new PaymentProcessor().chargeCustomer("42", BigDecimal.valueOf(42)));

    try (MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {

      // every object creation is returning a mock from now on
      PaymentProcessor paymentProcessor = new PaymentProcessor();

      when(paymentProcessor.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

      assertEquals(BigDecimal.TEN, paymentProcessor.chargeCustomer("42", BigDecimal.valueOf(42)));
    }

    // a real object of the PaymentProcessor is returned
    System.out.println(new PaymentProcessor().chargeCustomer("42", BigDecimal.valueOf(42)));
  }

  @Test
  void mockDifferentObjectConstruction() {
    try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstruction(PaymentProcessor.class)) {

      PaymentProcessor firstInstance = new PaymentProcessor("PayPal", BigDecimal.TEN);
      PaymentProcessor secondInstance = new PaymentProcessor(true, "PayPal", BigDecimal.TEN);

      when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
      when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

      assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
      assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
      assertEquals(2, mocked.constructed().size());
    }
  }

  @Test
  void mockDifferentObjectConstructionWithAnswer() {
    try (MockedConstruction<PaymentProcessor> mocked = Mockito.mockConstructionWithAnswer(PaymentProcessor.class,
      // default answer for the first mock
      invocation -> new BigDecimal("500.00"),
      // additional answer for all further mocks
      invocation -> new BigDecimal("42.00"))) {

      PaymentProcessor firstInstance = new PaymentProcessor();
      PaymentProcessor secondInstance = new PaymentProcessor();
      PaymentProcessor thirdInstance = new PaymentProcessor();

      assertEquals(new BigDecimal("500.00"), firstInstance.chargeCustomer("42", BigDecimal.ZERO));
      assertEquals(new BigDecimal("42.00"), secondInstance.chargeCustomer("42", BigDecimal.ZERO));
      assertEquals(new BigDecimal("42.00"), thirdInstance.chargeCustomer("42", BigDecimal.ZERO));
    }
  }
}
