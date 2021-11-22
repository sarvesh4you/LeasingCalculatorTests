
package com.seb.automation.getpageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import static com.seb.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.seb.automation.utils.PropertyFileReaderAndWriter.getPropertyFromConfig;

public class BaseUi {

  WebDriver driver;
  protected WebDriverWait wait;
  private String pageName;

  protected BaseUi(WebDriver driver, String pageName) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.pageName = pageName;
    this.wait = new WebDriverWait(driver, Integer.parseInt(getPropertyFromConfig("timeout")));
  }

  protected void logMessage(String message) {
    Reporter.log(message, true);
  }

  /** Verification of the page title with the title text provided in the page object repository */
  protected void verifyPageTitleContains() {
    String expectedPagetitle = getPageTitleFromFile(pageName).trim();
    try {

      wait.until(ExpectedConditions.titleContains(expectedPagetitle));
      logMessage("TEST PASSED: PageTitle contains: '" + expectedPagetitle + "'.");
    } catch (TimeoutException exp) {
      String actualPageTitle = driver.getTitle().trim();
      logMessage(
          "TEST FAILED: As actual Page Title: '"
              + actualPageTitle
              + "' does not contain expected Page Title : '"
              + expectedPagetitle
              + "'.");
    }
  }

  /** Scroll down page up to the element */
  protected void scrollDown(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /** Click on the element */
  protected void click(WebElement element) {
    try {
      wait.until(ExpectedConditions.visibilityOf(element));
      scrollDown(element);
      element.click();
    } catch (StaleElementReferenceException ex1) {
      wait.until(ExpectedConditions.visibilityOf(element));
      scrollDown(element);
      element.click();
      logMessage("Clicked Element " + element + " after catching Stale Element Exception");
    } catch (Exception ex2) {
      logMessage("Element " + element + " could not be clicked! " + ex2.getMessage());
    }
  }

  /** Enter text into the textfield */
  protected void enterText(WebElement element, String text) {
    wait.until(ExpectedConditions.visibilityOf(element));
    element.click();
    element.clear();
    element.sendKeys(text);
  }

  	protected void switchToTheFirstFrame(){
  		driver.switchTo().frame(0);
  	}


  protected void switchToDefaultFrame(){
    driver.switchTo().defaultContent();
  }

}
