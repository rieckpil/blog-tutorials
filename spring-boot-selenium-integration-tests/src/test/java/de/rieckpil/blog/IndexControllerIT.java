package de.rieckpil.blog;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class IndexControllerIT {

  @Container
  public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer().withCapabilities(new ChromeOptions());

  @Test
  public void shouldDisplayMessage() {
    WebElement messageElement = this.chrome.getWebDriver().findElementById("message");
    assertEquals("Integration Test with Selenium", messageElement.getText());
  }

}
