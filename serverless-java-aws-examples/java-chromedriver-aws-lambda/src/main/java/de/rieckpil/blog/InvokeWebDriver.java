package de.rieckpil.blog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InvokeWebDriver implements RequestHandler<String[], String> {

  @Override
  public String handleRequest(String[] input, Context context) {

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments(
      "--whitelisted-ips",
      "--disable-gpu",
      "--headless",
      "--single-process",
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
