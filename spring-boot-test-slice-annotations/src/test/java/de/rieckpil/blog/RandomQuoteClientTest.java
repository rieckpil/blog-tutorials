package de.rieckpil.blog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestClientTest(RandomQuoteClient.class)
class RandomQuoteClientTest {

  @Autowired
  private RandomQuoteClient randomQuoteClient;

  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Test
  public void shouldReturnQuoteFromRemoteSystem() {
    String response = "{" +
      "\"contents\": {"+
        "\"quotes\": ["+
          "{"+
            "\"author\": \"duke\"," +
            "\"quote\": \"Lorem ipsum\""+
          "}"+
        "]"+
      "}" +
    "}";

    this.mockRestServiceServer
      .expect(MockRestRequestMatchers.requestTo("/qod"))
      .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));

    String result = randomQuoteClient.getRandomQuote();

    assertEquals("Lorem ipsum", result);
  }
}
