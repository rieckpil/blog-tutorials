package de.rieckpil.blog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Disabled
public class RealUsageTest {

  @Test
  void testRealUsage() {

    String baseUrl = "https://quotes.rest";

    OkHttpClientExample okHttpClientExample = new OkHttpClientExample(baseUrl);
    ApacheHttpClient apacheHttpClient = new ApacheHttpClient(baseUrl);
    JavaHttpClient javaHttpClient = new JavaHttpClient(baseUrl);

    assertFalse(okHttpClientExample.getRandomQuote().contains("Lorem ipsum"));
    assertFalse(apacheHttpClient.getRandomQuote().contains("Lorem ipsum"));
    assertFalse(javaHttpClient.getRandomQuote().contains("Lorem ipsum"));
  }
}
