package de.rieckpil.blog;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("stocks")
public class StockResource {

    @GET
    public Response getAllStocks() {
        JsonObject json = Json.createObjectBuilder().add("name", "SHA").add("price", 15.03).build();
        return Response.ok(json.toString()).build();
    }
}
