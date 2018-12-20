package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("secure")
@Stateless
public class VerySecureResource {

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @Inject
    private JsonWebToken callerPrincipal;

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    public Response message() {
        return Response.ok(callerPrincipal.getName() + "is allowed to read message: " + message).build();
    }

}
