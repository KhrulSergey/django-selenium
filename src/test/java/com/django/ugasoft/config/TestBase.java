package com.django.ugasoft.config;

import com.django.ugasoft.Application;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Application.class)
public class TestBase extends AbstractTestNGSpringContextTests {

  @Value("${browser}")
  private String browserType = "chrome";

  protected WebDriver driver;

  @BeforeTest
  public void setUp() {
    initDriver();
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }

  public void initDriver() {
    driver = BrowserFactory.getDriver(browserType);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }
}
