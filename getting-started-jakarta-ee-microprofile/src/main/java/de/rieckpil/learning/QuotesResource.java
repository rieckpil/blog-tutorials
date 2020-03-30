package de.rieckpil.learning;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("quotes")
public class QuotesResource {

  @Inject
  @RestClient
  private QuotesClient quotesClient;

  @GET
  @Metered
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuoteOfTheDay() {

    JsonObject quoteOfTheDay = quotesClient.getQuoteOfTheDay();
    JsonPointer quotePointer = Json.createPointer("/contents/quotes/0/quote");
    JsonObject result = Json.createObjectBuilder()
      .add("quoteOfTheDay", quotePointer.getValue(quoteOfTheDay)).build();

    return Response.ok(result).build();
  }
}
