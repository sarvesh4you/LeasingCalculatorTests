package com.seb.automation;

import com.seb.leaseCalculator.keywords.LeaseCalcPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.seb.automation.utils.PropertyFileReaderAndWriter.getPropertyFromConfig;

public class TestSessionInitiator {

  protected WebDriver driver;
  private final WebDriverFactory wdfactory;
  String browser;
  String appbaseurl;
  String chromedriverpath;
  static int timeout;
  Map<String, Object> chromeOptions = null;
  DesiredCapabilities capabilities;

  /** Initiating the page objects */
  public LeaseCalcPageActions leaseCalcPage;

  public WebDriver getDriver() {
    return this.driver;
  }

  private void _initPage() {
    leaseCalcPage = new LeaseCalcPageActions(driver);
  }

  /** Page object Initiation done */
  public TestSessionInitiator() {
    wdfactory = new WebDriverFactory();

    _configureBrowser();
    _initPage();
  }

  private void _configureBrowser() {
    driver = wdfactory.getDriver(_getSessionConfig());
    driver.manage().window().maximize();
    driver
        .manage()
        .timeouts()
        .implicitlyWait(Integer.parseInt(getPropertyFromConfig("timeout")), TimeUnit.SECONDS);
  }

  private Map<String, String> _getSessionConfig() {
    String[] configKeys = {"browser", "timeout", "driverpath"};
    Map<String, String> config = new HashMap<String, String>();
    for (String string : configKeys) {
      config.put(string, getPropertyFromConfig(string));
    }
    return config;
  }

  public void launchApplication(String baseurl) {
    Reporter.log("\nApplication url is :- " + baseurl, true);
    Reporter.log("Test browser is :- " + _getSessionConfig().get("browser") + "\n", true);
    driver.manage().deleteAllCookies();
    driver.get(baseurl);
  }

  public void stepStartMessage(String testStepName) {
    Reporter.log(" ", true);
    Reporter.log(
        "=================================================================================================",
        true);
    Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase() + " *****", true);
    Reporter.log(
        "=================================================================================================",
        true);
    Reporter.log(" ", true);
  }

  public void closeBrowserSession() throws IOException {
    Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
    driver.close();
  }
}
