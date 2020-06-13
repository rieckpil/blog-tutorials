package de.rieckpil.blog;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleScheduledHandler implements RequestHandler<Void, Void> {

  @Override
  public Void handleRequest(Void input, Context context) {

    System.out.println("About to check availability of https://rieckpil.de");

    HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();

    try {
      HttpRequest request = HttpRequest
        .newBuilder(new URI("https://rieckpil.de"))
        .timeout(Duration.ofSeconds(2))
        .GET()
        .build();

      HttpResponse<String> result = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      if(result.statusCode() != 200) {
        // inform me via e.g. Slack or Telegram
      } else {
        System.out.println("Blog is up- and running");
      }

    }
    catch (URISyntaxException | IOException | InterruptedException e) {
      // inform me via e.g. Slack or Telegram
      e.printStackTrace();
    }

    return null;
  }
}
