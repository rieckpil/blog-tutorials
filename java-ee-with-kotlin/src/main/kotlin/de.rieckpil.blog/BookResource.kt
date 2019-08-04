package de.rieckpil.blog

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
class BookResource {

    @Inject
    private lateinit var bookService: BookService;

    @GET
    fun getAllBooks(): Response = Response.ok(bookService.getAllBooks()).build()

    @GET
    @Path("/{id}")
    fun getBookById(@PathParam("id") id: Long): Response {
        var book: Book? = bookService.getBookById(id) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(book).build()
    }
}