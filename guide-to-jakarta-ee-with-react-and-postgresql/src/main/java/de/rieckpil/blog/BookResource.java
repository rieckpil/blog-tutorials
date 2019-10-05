package de.rieckpil.blog;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("books")
public class BookResource {

    @Inject
    @ConfigProperty(name = "book_list_size", defaultValue = "10")
    private Integer bookListSize;

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {

        List<Book> allBooks = this.entityManager
                .createQuery("SELECT b FROM Book b", Book.class)
                .setMaxResults(bookListSize)
                .getResultList();

        return Response.ok(allBooks).build();
    }
}
