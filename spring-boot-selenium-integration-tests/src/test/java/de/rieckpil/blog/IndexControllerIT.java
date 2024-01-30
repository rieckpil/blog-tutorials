package de.rieckpil.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

@ExtendWith({ScreenshotOnFailureExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerIT {

  static BrowserWebDriverContainer<?> container =
      new BrowserWebDriverContainer<>().withCapabilities(new ChromeOptions());

  @LocalServerPort private int port;

  @BeforeAll
  static void beforeAll(@Autowired Environment environment) {
    Testcontainers.exposeHostPorts(environment.getProperty("local.server.port", Integer.class));
    container.start();
  }

  @Test
  void shouldDisplayMessage() {
    container
        .getWebDriver()
        .get(String.format("http://host.testcontainers.internal:%d/index", port));

    WebElement messageElement = container.getWebDriver().findElement(By.id("message"));

    assertEquals("Integration Test with Selenium", messageElement.getText());
  }
}
