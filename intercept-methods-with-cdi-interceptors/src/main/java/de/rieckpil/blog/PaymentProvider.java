package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.math.BigDecimal;

@Startup
@Singleton
@ManipulatedPayment
public class PaymentProvider {

    @PostConstruct
    public void setUpPaymentProvider() {
        System.out.println("Setting up payment provider ...");
    }

    public void withdrawMoneyFromCustomer(String customer, BigDecimal amount) {
        System.out.println("Withdrawing money from " + customer + " - amount: " + amount);
    }
}
