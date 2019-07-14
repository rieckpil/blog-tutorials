package de.rieckpil.blog;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Path("payments")
public class PaymentResource {

    @Inject
    private PaymentProvider paymentProvider;

    @GET
    @Path("/{customerName}")
    @SecurePayment(requiredHttpHeader = "X-Secure-Payment")
    public Response getPaymentForCustomer(@PathParam("customerName") String customerName) {

        paymentProvider
                .withdrawMoneyFromCustomer(customerName,
                        new BigDecimal(42.00).setScale(2, RoundingMode.HALF_UP));

        return Response.ok("Payment was withdrawn from customer " + customerName).build();
    }

}
