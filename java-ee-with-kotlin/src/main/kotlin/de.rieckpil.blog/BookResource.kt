package de.rieckpil.blog

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("books")
class BookResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun getBook(): Response {
        return Response.ok("Hello World!").build();
    }


}