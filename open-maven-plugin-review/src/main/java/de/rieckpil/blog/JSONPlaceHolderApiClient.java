package de.rieckpil.blog;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "https://jsonplaceholder.typicode.com/")
public interface JSONPlaceHolderApiClient {

    @GET
    @Path("/users")
    JsonArray getAllUsers();
}
