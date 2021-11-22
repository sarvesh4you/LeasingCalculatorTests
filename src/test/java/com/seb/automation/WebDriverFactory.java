/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seb.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;

public class WebDriverFactory {
  private static String browser;

  /** Getting browser preference */
  public WebDriver getDriver(Map<String, String> seleniumconfig) {
    browser = seleniumconfig.get("browser");
    if (browser.equalsIgnoreCase("firefox")) {
      return getChromeDriver(seleniumconfig.get("driverpath"));
    } else if (browser.equalsIgnoreCase("chrome")) {
      return getChromeDriver(seleniumconfig.get("driverpath"));
    }
    return new FirefoxDriver();
  }

  /** Setting chromedriver properties */
  private static WebDriver getChromeDriver(String driverpath) {
    System.setProperty("webdriver.chrome.driver", driverpath);
    ChromeOptions options = new ChromeOptions();
    options.setCapability("disable-popup-blocking", "true");
    options.addArguments("--disable-extensions");
    options.addArguments("ignore-certificate-errors");
    options.addArguments("allow-running-insecure-content");
    return new ChromeDriver(options);
  }
}
