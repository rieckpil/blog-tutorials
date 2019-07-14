package de.rieckpil.blog;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PaymentManipulator {

    public void manipulatePayment() {
        System.out.println("Manipulating payment...");
    }

}
