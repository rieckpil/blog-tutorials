package de.rieckpil.blog;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@Provider
public class RateLimitingFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Filtering request!!");

        SecurityContext securityContext = requestContext.getSecurityContext();
        Principal principal = securityContext.getUserPrincipal();

    }
}
