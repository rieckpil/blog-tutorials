package de.rieckpil.blog;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ejb.Singleton;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/persons")
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    public Response getAllPersons() {
        var allPersons = entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
        return Response.ok(allPersons).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        var personById = entityManager.find(Person.class, id);

        if (personById == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(personById).build();
    }

    @POST
    public Response createNewPerson(@Context UriInfo uriInfo, @RequestBody JsonObject jsonObject) {

        var personToStore = new Person(jsonObject);
        entityManager.persist(personToStore);

        var headerLocation = uriInfo.getAbsolutePathBuilder()
                .path(personToStore.getId().toString())
                .build();

        return Response.created(headerLocation).build();
    }
}
