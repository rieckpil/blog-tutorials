package de.rieckpil.blog;

import java.io.File;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class SeleniumExampleTest {

  private static final Network network = Network.newNetwork();

  // Serve a static page from a container on a shared network so the test does
  // not depend on any external website being reachable or unchanged.
  @Container
  static GenericContainer<?> webServerContainer =
    new GenericContainer<>("nginx:1.27-alpine")
      .withNetwork(network)
      .withNetworkAliases("web")
      .withCopyFileToContainer(
        MountableFile.forClasspathResource("web/index.html"),
        "/usr/share/nginx/html/index.html")
      .waitingFor(Wait.forHttp("/").forStatusCode(200));

  @Container
  static BrowserWebDriverContainer<?> webDriverContainer =
    new BrowserWebDriverContainer<>()
      .withNetwork(network)
      .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("./target"))
      .withCapabilities(new ChromeOptions()
        .addArguments("--no-sandbox")
        .addArguments("--disable-dev-shm-usage"));

  @Test
  void shouldAccessHomePage() {
    Configuration.timeout = 2000;
    Configuration.baseUrl = "http://web";
    Configuration.reportsFolder = "target/selenide-reports";

    RemoteWebDriver remoteWebDriver = webDriverContainer.getWebDriver();
    WebDriverRunner.setWebDriver(remoteWebDriver);

    open("/");

    screenshot("home-page");

    String h1Text = $(By.tagName("h1")).text();

    assertThat(h1Text)
      .isEqualTo("Testcontainers Selenium Example");
  }
}
