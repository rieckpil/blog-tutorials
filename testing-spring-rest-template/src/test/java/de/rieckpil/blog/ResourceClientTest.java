package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(ResourceClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class ResourceClientTest {

  @Autowired private ResourceClient resourceClient;

  @Autowired private MockRestServiceServer mockRestServiceServer;

  @BeforeEach
  // @Before for JUnit 4
  void setUp() {
    this.mockRestServiceServer.reset();
  }

  @AfterEach
  // @After for JUnit 4
  void tearDown() {
    this.mockRestServiceServer.verify();
  }

  @Test
  void successfullyReturnData() {

    String json = """
        {
          "data": "duke"
        }
      """;

    this.mockRestServiceServer
        .expect(requestTo("/api/unkown/1"))
        .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    JsonNode result = resourceClient.getSingleResource(1L);

    assertNotNull(result);
  }
}
