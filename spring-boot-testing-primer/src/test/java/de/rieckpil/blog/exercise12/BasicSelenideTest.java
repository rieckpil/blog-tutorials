package de.rieckpil.blog.exercise12;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasicSelenideTest {

  @LocalServerPort
  private int port;

  @Test
  void shouldAccessDashboardAndSubmitForm() {

    Configuration.browserCapabilities = new ChromeOptions()
      .addArguments("--no-sandbox")
      .addArguments("--disable-dev-shm-usage");

    Selenide.open("http://localhost:" + port + "/dashboard");

    assertEquals("Dashboard", Selenide.title());

    $(By.id("lname")).val("Mike");
    $(By.id("fname")).val("Duke");

    $(By.id("submit")).click();

    screenshot("post-submit");
  }
}
