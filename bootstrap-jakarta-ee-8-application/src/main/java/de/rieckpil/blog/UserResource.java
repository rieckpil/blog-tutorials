package de.rieckpil.blog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("users")
@ApplicationScoped
public class UserResource {

    @Inject
    private UserProvider userProvider;

    @GET
    public Response getAllUsers() {
        return Response.ok(userProvider.getAllUsers()).build();
    }
}
