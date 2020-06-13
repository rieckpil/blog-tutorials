package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@ExtendWith({ScreenshotOnFailureExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerIT {

  @LocalServerPort
  private int port;

  @Container
  private BrowserWebDriverContainer container = new BrowserWebDriverContainer()
    .withCapabilities(new ChromeOptions());

  @Test
  public void shouldDisplayMessage() {
    // depending on your operation system try 172.17.0.1 as IP or container.getTestHostIpAddress()
    this.container.getWebDriver().get("http://host.docker.internal:" + port + "/index");
    WebElement messageElement = this.container.getWebDriver().findElementById("message");
    assertEquals("Integration Test with Selenium", messageElement.getText());
  }
}
