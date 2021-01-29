package de.rieckpil.blog;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();

    configurableApplicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

    configurableApplicationContext.addApplicationListener(applicationEvent -> {
      if (applicationEvent instanceof ContextClosedEvent) {
        wireMockServer.stop();
      }
    });

    TestPropertyValues
      .of("todo_url:http://localhost:" + wireMockServer.port() + "/")
      .applyTo(configurableApplicationContext);

    wireMockServer.stubFor(
      WireMock.get("/todos")
        .willReturn(aResponse()
          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
          .withBody("[{\"userId\": 1,\"id\": 1,\"title\": \"Learn Spring Boot 3.0\", \"completed\": false}," +
            "{\"userId\": 1,\"id\": 2,\"title\": \"Learn WireMock\", \"completed\": true}]"))
    );

    wireMockServer.stubFor(
      WireMock.post("/todos")
        .willReturn(aResponse()
          .withStatus(201)
          .withHeader("Location", "http://localhost:" + wireMockServer.port() + "/todos42"))
    );

  }
}
