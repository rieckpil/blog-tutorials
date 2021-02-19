package de.rieckpil.blog.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import de.rieckpil.blog.client.JavaHttpClient;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

  @AfterEach
  void tearDown() {
    wireMockServer.resetAll();
  }

  @Test
  void shouldReturnRandomQuoteOn200Response() throws IOException {

    wireMockServer.stubFor(
      WireMock.get("/qod")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody(new String(JavaHttpClientTest.class
            .getClassLoader()
            .getResourceAsStream("stubs/random-quote-success.json")
            .readAllBytes())))
    );

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }

  @Test
  void shouldReturnDefaultQuoteOnRequestFailure() {

    wireMockServer.stubFor(
      WireMock.get("/qod")
        .willReturn(aResponse()
          .withStatus(500)
          .withBody("Server Down!")
          .withFixedDelay(2_000))
    );

    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Lorem ipsum dolor sit amet.");
  }

  @Test
  void advancedStubbings() throws IOException {

    wireMockServer.stubFor(
      WireMock.any(anyUrl())
        .withHeader("Accept", containing("json"))
        .atPriority(1)
        .willReturn(aResponse().withStatus(500))
    );

    wireMockServer.stubFor(
      WireMock.get(anyUrl())
        .atPriority(10)
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody(new String(JavaHttpClientTest.class
            .getClassLoader()
            .getResourceAsStream("stubs/random-quote-success.json")
            .readAllBytes())))
    );

    // the first stubbing with priority '1' will be used
    String randomQuote = this.cut.getRandomQuote();

    assertEquals(randomQuote, "Lorem ipsum dolor sit amet.");
  }

  @Test
  void shouldReturnRandomQuoteOn200ResponseVerification() throws IOException {

    wireMockServer.stubFor(
      WireMock.get("/qod")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody(new String(JavaHttpClientTest.class
            .getClassLoader()
            .getResourceAsStream("stubs/random-quote-success.json")
            .readAllBytes())))
    );

    String randomQuote = this.cut.getRandomQuote();

    wireMockServer.verify(exactly(1), getRequestedFor(urlEqualTo("/qod"))
      .withHeader("Accept", equalTo("application/json")));

    List<ServeEvent> allServeEvents = wireMockServer.getAllServeEvents();

    LoggedRequest request = allServeEvents.get(0).getRequest();

    assertEquals("", request.getBodyAsString());
    assertEquals(7, request.getAllHeaderKeys().size());

    List<LoggedRequest> unmatchedRequests = wireMockServer.findAllUnmatchedRequests();

    assertEquals(0, unmatchedRequests.size());

    assertEquals(randomQuote, "Vision without action is daydream. Action without vision is nightmare");
  }

  @Test
  void shouldNotMatchStubbing() {

    wireMockServer.stubFor(
      WireMock.get("/differentUrl")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("duke42"))
    );

    String randomQuote = this.cut.getRandomQuote();
  }
}
