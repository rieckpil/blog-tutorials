package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;

public class InvokeWebDriver implements RequestHandler<String[], String> {

  @Override
  public String handleRequest(String[] input, Context context) {

    System.setProperty("webdriver.chrome.verboseLogging", "true");

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("load-extension"));

    chromeOptions.addArguments(
      "--headless",
      "--remote-debugging-port=0",
      "--disable-extensions",
      "--disable-dev-shm-usage",
      "--no-sandbox");

    WebDriver driver = new ChromeDriver(chromeOptions);

    for (String url : input) {
      driver.get(url);
      System.out.println(driver.getTitle());
    }

    driver.quit();

    return "Successfully scraped page titles!";
  }
}
