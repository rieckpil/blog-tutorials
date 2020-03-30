package de.rieckpil.learning;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(baseUri = "https://quotes.rest/")
public interface QuotesClient {

  @GET
  @Path("qod")
  @Retry(maxRetries = 3, maxDuration = 3000L)
  @Fallback(fallbackMethod = "getFallbackQuote")
  JsonObject getQuoteOfTheDay();


  default JsonObject getFallbackQuote() {
    return Json.createObjectBuilder()
      .add("contents",
        Json.createObjectBuilder()
          .add("quotes", Json.createArrayBuilder()
            .add(Json.createObjectBuilder().add("quote", "Lorem ipsum dolor sit amet"))))
      .build();
  }

}
