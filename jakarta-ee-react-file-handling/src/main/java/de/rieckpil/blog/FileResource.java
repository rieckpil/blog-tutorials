package de.rieckpil.blog;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("files")
@ApplicationScoped
public class FileResource {

  private List<FileContent> inMemoryFileStore = new ArrayList();

  @GET
  public Response getRandomFile() {
    FileContent randomFile = inMemoryFileStore.get(ThreadLocalRandom.current().nextInt(0, inMemoryFileStore.size()));

    return Response
      .ok(randomFile.getContent())
      .type(MediaType.APPLICATION_OCTET_STREAM)
      .header(HttpHeaders.CONTENT_LENGTH, randomFile.getContent().length)
      .header(HttpHeaders.CONTENT_TYPE, "attachment; filename=\"" + randomFile.getFileName() + "\"")
      .build();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response uploadNewFile(@Context UriInfo uriInfo) {
    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
