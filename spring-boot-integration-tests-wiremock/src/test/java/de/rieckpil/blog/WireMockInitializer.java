package de.rieckpil.blog;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

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
      .of("todo_url:http://localhost:" + wireMockServer.port() + "/todos")
      .applyTo(configurableApplicationContext);
  }
}
