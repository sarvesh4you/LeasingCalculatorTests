package com.seb.automation.getpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.seb.automation.getpageobjects.ObjectFileReader.getELementFromFile;
import static org.testng.Assert.fail;

public class GetPage extends BaseUi {

  protected WebDriver webdriver;
  String pageName;

  public GetPage(WebDriver driver, String pageName) {
    super(driver, pageName);
    this.webdriver = driver;
    this.pageName = pageName;
  }

  protected WebElement element(String elementToken) {
    return element(elementToken, "");
  }

  protected WebElement element(String elementToken, String replacement)
      throws NoSuchElementException {
    WebElement elem = null;
    try {
      elem =
          wait.until(
              ExpectedConditions.visibilityOf(
                  webdriver.findElement(getLocator(elementToken, replacement))));

    } catch (NoSuchElementException excp) {
      fail("FAILED: Element '" + elementToken + "' not found on the " + this.pageName + " !!!");
    }
    return elem;
  }

  protected List<WebElement> elements(String elementToken) throws NoSuchElementException {
    List<WebElement> elem = null;
    String replacement = "";
    try {
      elem = webdriver.findElements(getLocator(elementToken, replacement));

    } catch (NoSuchElementException excp) {
      fail("FAILED: Element '" + elementToken + "' not found on the " + this.pageName + " !!!");
    }
    return elem;
  }

  protected By getLocator(String elementToken, String replacement) {
    String[] locator = getELementFromFile(this.pageName, elementToken);
    locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
    return getBy(locator[1].trim(), locator[2].trim());
  }

  private By getBy(String locatorType, String locatorValue) {
    switch (Locators.valueOf(locatorType)) {
      case id:
        return By.id(locatorValue);
      case xpath:
        return By.xpath(locatorValue);
      case css:
        return By.cssSelector(locatorValue);
      case name:
        return By.name(locatorValue);
      case classname:
        return By.className(locatorValue);
      case linktext:
        return By.linkText(locatorValue);
      default:
        return By.id(locatorValue);
    }
  }
}
