package de.rieckpil.blog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpClientExample {

  private static final String DEFAULT_QUOTE = "Lorem ipsum dolor sit amet.";
  private final String baseUrl;

  public OkHttpClientExample(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getRandomQuote() {

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
      .url(baseUrl + "/qod")
      .addHeader("Accept", "application/json")
      .build();

    try (Response response = client.newCall(request).execute()) {

      if (response.code() != 200) {
        return DEFAULT_QUOTE;
      }

      RandomQuoteResponse responseBody = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .readValue(response.body().string(), RandomQuoteResponse.class);

      return responseBody.getContents().getQuotes().get(0).getQuote();
    } catch (IOException e) {
      e.printStackTrace();
      return DEFAULT_QUOTE;
    }
  }
}
