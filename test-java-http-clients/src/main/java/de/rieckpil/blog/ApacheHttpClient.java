package de.rieckpil.blog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApacheHttpClient {

  private static final String DEFAULT_QUOTE = "Lorem ipsum dolor sit amet.";
  private final String baseUrl;

  public ApacheHttpClient(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getRandomQuote() {

    HttpClient client = HttpClientBuilder.create().build();

    try {
      HttpUriRequest request = RequestBuilder.get()
        .setUri(this.baseUrl + "/qod")
        .setHeader(HttpHeaders.ACCEPT, "application/json")
        .build();

      HttpResponse httpResponse = client.execute(request);

      if (httpResponse.getStatusLine().getStatusCode() != 200) {
        return DEFAULT_QUOTE;
      }

      RandomQuoteResponse responseBody = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .readValue(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"), RandomQuoteResponse.class);

      return responseBody.getContents().getQuotes().get(0).getQuote();
    } catch (IOException e) {
      e.printStackTrace();
      return DEFAULT_QUOTE;
    }
  }
}
