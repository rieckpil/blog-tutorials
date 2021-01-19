package de.rieckpil.blog.constructor;

import java.math.BigDecimal;

public class CheckoutService {

  public BigDecimal purchaseProduct(String productName, String customerId) {
    // any arbitrary implementation
    PaymentProcessor paymentProcessor = new PaymentProcessor();

    return paymentProcessor.chargeCustomer(customerId, BigDecimal.TEN);
  }
}
