package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleResource {

    @Inject
    @RestClient
    private JSONPlaceHolderApiClient jsonPlaceHolderApiClient;

    @Inject
    @ConfigProperty(name = "MY_MESSAGE")
    private String message;

    @Inject
    private SampleService sampleService;

    @GET
    public Response message() {
        return Response
                .ok(this.sampleService.modifyString(message))
                .build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(jsonPlaceHolderApiClient.getAllUsers()).build();
    }

}
