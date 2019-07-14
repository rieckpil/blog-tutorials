package de.rieckpil.blog;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Priority(42)
@Interceptor
@SecurePayment
public class SecurePaymentInterceptor {

    @Context
    private HttpHeaders headers;

    @AroundInvoke
    public Object securePayment(InvocationContext invocationContext) throws Exception {

        String requiredHttpHeader = invocationContext
                .getMethod()
                .getAnnotation(SecurePayment.class)
                .requiredHttpHeader();

        if (headers.getRequestHeaders().containsKey(requiredHttpHeader)) {
            return invocationContext.proceed();
        } else {
            throw new WebApplicationException("Missing HTTP header: " + requiredHttpHeader, Response.Status.BAD_REQUEST);
        }

    }
}
