package de.rieckpil.blog;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@Testcontainers
class MockServerExampleTest {

  @Container
  static MockServerContainer mockServer =
    new MockServerContainer(DockerImageName.parse("mockserver/mockserver:5.14.0"));

  static final HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .build();

  @Test
  void shouldStubHttpCall() throws Exception {
    new MockServerClient(mockServer.getHost(), mockServer.getServerPort())
      .when(request()
        .withPath("/orders"))
      .respond(response()
        .withBody("""
            [{
             "orderId": 42,
             "customerId": "XYZ1445"
            }]
          """, MediaType.APPLICATION_JSON_UTF_8));

    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(mockServer.getEndpoint() + "/orders"))
      .setHeader("Accept", "application/json")
      .build();

    HttpResponse<String> response = httpClient
      .send(request, HttpResponse.BodyHandlers.ofString());

    System.out.println("Response: " + response.body());

    assertThat(new ObjectMapper().readValue(response.body(), ArrayNode.class))
      .hasSize(1);
  }
}
