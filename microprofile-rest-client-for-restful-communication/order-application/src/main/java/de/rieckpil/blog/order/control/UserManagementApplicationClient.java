package de.rieckpil.blog.order.control;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RegisterRestClient
@Path("/resources/users")
@Produces("application/json")
@Consumes("application/json")
@ClientHeaderParam(name = "User-Agent", value = "ORDER-MGT-APP")
public interface UserManagementApplicationClient {

    @GET
    @Path("/{userId}")
    JsonObject getUserById(@HeaderParam("X-Request-ID") String requestIdHeader, @PathParam("userId") Integer userId);

    @POST
    @ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")
    Response createUser(Integer userId);

    default String generateAuthHeader() {
        return UUID.randomUUID().toString();
    }
}
