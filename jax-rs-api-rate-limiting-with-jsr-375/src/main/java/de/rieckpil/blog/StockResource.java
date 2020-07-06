package de.rieckpil.blog;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("stocks")
public class StockResource {

  @GET
  @RolesAllowed("USER")
  public Response getAllStocks() {
    JsonObject json = Json.createObjectBuilder().add("name", "Alphabet Inc.").add("price", 1220.5).build();
    return Response.ok(json.toString()).build();
  }
}
