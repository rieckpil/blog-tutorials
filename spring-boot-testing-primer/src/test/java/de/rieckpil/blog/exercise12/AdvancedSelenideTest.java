package de.rieckpil.blog.exercise12;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.lang.SystemUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdvancedSelenideTest {

  @LocalServerPort
  private Integer port;

  @Container
  static BrowserWebDriverContainer<?> webDriverContainer =
    new BrowserWebDriverContainer<>()
      .withCapabilities(new ChromeOptions()
        .addArguments("--no-sandbox")
        .addArguments("--disable-dev-shm-usage"));

  @Test
  public void shouldDisplayBook() {

    Configuration.baseUrl = SystemUtils.IS_OS_WINDOWS ? "http://host.docker.internal:" : "http://172.17.0.1:";
    Configuration.baseUrl = Configuration.baseUrl + port;

    RemoteWebDriver remoteWebDriver = webDriverContainer.getWebDriver();
    WebDriverRunner.setWebDriver(remoteWebDriver);

    Selenide.open("/dashboard");

    assertEquals("Dashboard", Selenide.title());

    $(By.id("lname")).val("Mike");
    $(By.id("fname")).val("Duke");

    $(By.id("submit")).click();

    screenshot("post-submit");
  }
}
