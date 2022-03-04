package de.rieckpil.blog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.codeborne.selenide.Selenide.*;
import static org.testcontainers.Testcontainers.exposeHostPorts;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookStoreYouTubeWT {

  @LocalServerPort
  private Integer port;

  static BrowserWebDriverContainer<?> webDriverContainer =
    new BrowserWebDriverContainer<>()
      .withCapabilities(new ChromeOptions()
        .addArguments("--no-sandbox")
        .addArguments("--disable-dev-shm-usage"));

  @RegisterExtension
  static ScreenShooterExtension screenShooterExtension =
    new ScreenShooterExtension().to("target/selenide");

  @BeforeAll
  static void beforeAll(@Autowired Environment environment) {
    exposeHostPorts(environment.getProperty("local.server.port", Integer.class));
    webDriverContainer.start();
  }

  @Test
  void shouldDisplayBook() {

    Configuration.timeout = 2000;
    Configuration.baseUrl = String.format("http://host.testcontainers.internal:%d", port);

    RemoteWebDriver remoteWebDriver = webDriverContainer.getWebDriver();
    WebDriverRunner.setWebDriver(remoteWebDriver);

    open("/book-store");

    $(By.id("all-books")).shouldNot(Condition.exist);

    screenshot("pre-book-fetch");

    $(By.id("fetch-books")).click();

    screenshot("post-book-fetch");

    $(By.id("all-books")).shouldBe(Condition.visible);
  }
}
