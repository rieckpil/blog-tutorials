package de.rieckpil.blog.user;

import com.github.javafaker.Faker;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("users")
@Consumes("application/json")
@Produces("application/json")
@ApplicationScoped
public class UserResource {

    private ConcurrentHashMap<Integer, String> userDatabase;
    private Faker randomUser;

    @PostConstruct
    public void init() {
        this.userDatabase = new ConcurrentHashMap<>();
        this.userDatabase.put(1, "Duke");
        this.userDatabase.put(2, "John");
        this.userDatabase.put(3, "Tom");

        this.randomUser = new Faker();
    }

    @GET
    @Path("/{userId}")
    public JsonObject getUserById(@PathParam("userId") Integer userId,
                                  @HeaderParam("X-Request-Id") String requestId,
                                  @HeaderParam("X-Application-Name") String applicationName) {

        System.out.println(
                String.format("External system with name '%s' " +
                        "and request id '%s' trying to access " +
                        "user with id '%s'", applicationName, requestId, userId));

        return Json
                .createObjectBuilder()
                .add("username", this.userDatabase.getOrDefault(userId, "Default User"))
                .build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public void createNewUser(JsonObject user) {
        this.userDatabase
                .put(user.getInt("userId"), this.randomUser.name().firstName());
    }
}
