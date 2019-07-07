package de.rieckpil.blog.order.control;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Base64;

@RegisterRestClient
@Path("/resources/users")
@Produces("application/json")
@Consumes("application/json")
@ClientHeaderParam(name = "X-Application-Name", value = "ORDER-MGT-APP")
public interface UserManagementApplicationClient {

    @GET
    @Path("/{userId}")
    JsonObject getUserById(@HeaderParam("X-Request-Id") String requestIdHeader, @PathParam("userId") Integer userId);

    @POST
    @ClientHeaderParam(name = "Authorization", value = "{generateAuthHeader}")
    Response createUser(JsonObject user);

    default String generateAuthHeader() {
        return "Basic " + new String(Base64.getEncoder().encode("duke:SECRET".getBytes()));
    }
}
