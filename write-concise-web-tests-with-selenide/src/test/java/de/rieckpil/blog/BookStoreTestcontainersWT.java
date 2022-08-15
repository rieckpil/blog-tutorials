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
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.utility.DockerImageName;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookStoreTestcontainersWT {

  public static BrowserWebDriverContainer<?> webDriverContainer =
    new BrowserWebDriverContainer<>(
      System.getProperty("os.arch").equals("aarch64") ?
        DockerImageName.parse("seleniarm/standalone-chromium")
          .asCompatibleSubstituteFor("selenium/standalone-chrome")
        : DockerImageName.parse("selenium/standalone-chrome:4.3.0-20220726")
    )
      .withCapabilities(new ChromeOptions()
        .addArguments("--no-sandbox")
        .addArguments("--disable-dev-shm-usage"));

  @RegisterExtension
  static ScreenShooterExtension screenShooterExtension =
    new ScreenShooterExtension().to("target/selenide");

  @LocalServerPort
  private Integer port;

  @BeforeAll
  static void beforeAll(@Autowired Environment environment) {
    Testcontainers.exposeHostPorts(environment.getProperty("local.server.port", Integer.class));
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
    $(By.id("fetch-books")).click();
    $(By.id("all-books")).shouldBe(Condition.visible);
  }
}
