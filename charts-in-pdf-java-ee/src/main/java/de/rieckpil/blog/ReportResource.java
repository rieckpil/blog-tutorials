package de.rieckpil.blog;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("reports")
@Stateless
public class ReportResource {

  @Inject
  private PdfGenerator pdfGenerator;

  @GET
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response createSimplePdfWithChart() throws IOException {
    return Response.ok(pdfGenerator.createPdf(), MediaType.APPLICATION_OCTET_STREAM)
      .header("Content-Disposition", "attachment; filename=\"simplePdf.pdf\"").build();

  }

}
