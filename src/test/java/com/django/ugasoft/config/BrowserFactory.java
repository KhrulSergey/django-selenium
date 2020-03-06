package com.django.ugasoft.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
  public static WebDriver getDriver(String browser) {
    WebDriver driver;
    switch (browser.toLowerCase()) {
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
      case "ie":
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        break;
      case "phantomjs":
        WebDriverManager.phantomjs().setup();
        driver = new PhantomJSDriver();
        break;
      case "chrome":
      default:
        WebDriverManager.chromedriver().version("80.0.3987.16").setup();
        driver = new ChromeDriver();
    } // end switch

    return driver;
  }
}