package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InvokeChrome implements RequestHandler<Void, String> {

  @Override
  public String handleRequest(Void input, Context context) {

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments(
      "--headless",
      "--single-process",
      "--disable-dev-shm-usage",
      "--no-sandbox");

    WebDriver driver = new ChromeDriver(chromeOptions);

    driver.get("https://rieckpil.de");
    String pageTitle = driver.getTitle();
    driver.quit();

    return pageTitle;
  }
}
