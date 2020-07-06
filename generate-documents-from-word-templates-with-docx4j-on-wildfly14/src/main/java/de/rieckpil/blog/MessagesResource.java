package de.rieckpil.blog;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("messages")
public class MessagesResource {

  @Inject
  DocxGenerator docxGenerator;

  @POST
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createNewDocxMessage(@Valid @NotNull UserInformation userInformation) {

    byte[] result;

    try {
      result = docxGenerator.generateDocxFileFromTemplate(userInformation);
    } catch (Exception e) {
      e.printStackTrace();
      return Response.serverError().build();
    }

    return Response.ok(result, MediaType.APPLICATION_OCTET_STREAM)
      .header("Content-Disposition", "attachment; filename=\"message.docx\"")
      .build();
  }
}
