package de.rieckpil.blog.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JavaHttpClientTest {

  private static WireMockServer wireMockServer;

  private JavaHttpClient cut;

  @BeforeAll
  static void startWireMock() {
    wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
    wireMockServer.start();
  }

  @AfterAll
  static void stopWireMock() {
    wireMockServer.stop();
  }

  @BeforeEach
  void setUp() {
    this.cut = new JavaHttpClient(wireMockServer.baseUrl());
  }

  @Test
  void shouldReturnRandomQuoteOn200Response() {

    String randomQuote = this.cut.getRandomQuote();

    assertNotNull(randomQuote);
  }

  @Test
  void shouldReturnDefaultQuoteOnRequestFailure() {

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Lorem ipsum dolor sit amet.");
  }

}
