package de.rieckpil.blog.initializer;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  private static final Logger LOG = LoggerFactory.getLogger(WireMockInitializer.class);

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {

    LOG.info("About to start the WireMockServer");

    WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
    wireMockServer.start();

    LOG.info("WireMockServer successfully started");

    applicationContext.addApplicationListener(applicationEvent -> {
      if (applicationEvent instanceof ContextClosedEvent) {
        LOG.info("Stopping the WireMockServer");
        wireMockServer.stop();
      }
    });

    TestPropertyValues
      .of(Map.of("clients.order-api.base-url", wireMockServer.baseUrl() + "/orders"))
      .applyTo(applicationContext);
  }
}
