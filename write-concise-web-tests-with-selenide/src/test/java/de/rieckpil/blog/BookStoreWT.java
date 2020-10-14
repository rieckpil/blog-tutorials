package de.rieckpil.blog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookStoreWT {

  @LocalServerPort
  private Integer port;

  @BeforeEach
  public void setup() {
    Configuration.timeout = 2000;
  }

  // does screenshots
  // includes WebDriverManager
  // readable wait conditions

  @Test
  public void shouldDisplayBooks() {
    open("http://localhost:" + port + "/book-store");

    $(By.id("all-books")).shouldNot(Condition.exist);
    $(By.id("fetch-books")).click();
    $(By.id("all-books")).shouldBe(Condition.visible);
  }
}
