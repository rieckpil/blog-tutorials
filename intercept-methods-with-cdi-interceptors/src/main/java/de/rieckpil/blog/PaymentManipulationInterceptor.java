package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Interceptor
@ManipulatedPayment
public class PaymentManipulationInterceptor {

    @Inject
    private PaymentManipulator paymentManipulator;

    @AroundInvoke
    public Object manipulatePayment(InvocationContext invocationContext) throws Exception {

        if (invocationContext.getParameters()[0] instanceof String) {
            if (((String) invocationContext.getParameters()[0]).equalsIgnoreCase("duke")) {
                paymentManipulator.manipulatePayment();
                invocationContext.setParameters(new Object[]{
                        "Duke", new BigDecimal(999.99).setScale(2, RoundingMode.HALF_UP)
                });
            }
        }

        return invocationContext.proceed();
    }

    @AroundConstruct
    public void aroundConstructInterception(InvocationContext invocationContext) throws Exception {
        System.out.println(invocationContext.getConstructor().getDeclaringClass() + " will be manipulated");
        invocationContext.proceed();
    }

    @PostConstruct
    public void postConstructInterception(InvocationContext invocationContext) throws Exception {
        System.out.println(invocationContext.getMethod().getDeclaringClass() + " is ready for manipulation");
        invocationContext.proceed();
    }

    @PreDestroy
    public void preDestroyInterception(InvocationContext invocationContext) throws Exception {
        System.out.println("Stopped manipulating of class " + invocationContext.getMethod().getDeclaringClass());
        invocationContext.proceed();
    }
}
