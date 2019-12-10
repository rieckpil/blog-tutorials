package de.rieckpil.blog;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("files")
@ApplicationScoped
public class FileResource {

  private List<FileContent> inMemoryFileStore = new ArrayList();

  @GET
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response downloadRandomFile() {

    if (inMemoryFileStore.size() == 0) {
      return Response.noContent().build();
    }

    FileContent randomFile = inMemoryFileStore.get(ThreadLocalRandom.current().nextInt(0, inMemoryFileStore.size()));

    return Response
      .ok(randomFile.getContent())
      .type(MediaType.APPLICATION_OCTET_STREAM)
      .header(HttpHeaders.CONTENT_LENGTH, randomFile.getContent().length)
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + randomFile.getFileName())
      .build();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response uploadNewFile(@FormDataParam("file") InputStream inputStream,
                                @FormDataParam("file") FormDataContentDisposition fileDetails,
                                @Context UriInfo uriInfo) throws IOException {

    this.inMemoryFileStore.add(new FileContent(fileDetails.getFileName(), inputStream.readAllBytes()));

    return Response.created(uriInfo.getAbsolutePath()).build();
  }
}
