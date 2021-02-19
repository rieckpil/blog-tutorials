package de.rieckpil.blog.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpClient {

  private static final String DEFAULT_QUOTE = "Lorem ipsum dolor sit amet.";
  private final String baseUrl;

  public JavaHttpClient(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getRandomQuote() {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(baseUrl + "/qod"))
      .header("Accept", "application/json")
      .GET()
      .build();

    try {
      HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

      if (httpResponse.statusCode() != 200) {
        return DEFAULT_QUOTE;
      }

      RandomQuoteResponse responseBody = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .readValue(httpResponse.body(), RandomQuoteResponse.class);

      String randomQuote = responseBody.getContents().getQuotes().get(0).getQuote();

      return randomQuote;
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      return DEFAULT_QUOTE;
    }
  }
}
