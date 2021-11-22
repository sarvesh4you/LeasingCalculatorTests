package com.seb.leaseCalculator.keywords;

import com.seb.automation.getpageobjects.GetPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class LeaseCalcPageActions extends GetPage {
  WebDriver driver;

  public LeaseCalcPageActions(WebDriver driver) {
    super(driver, "LeaseCalcPage");
    this.driver = driver;
  }

  /** Method which verifies that Lease Calculator Page is displayed */
  public void verifyUserIsOnLeaseCalcPage() {
    verifyPageTitleContains();
    element("lnk_genericFirstXpath", "I agree").click();
    element("txt_pageHeader").isDisplayed();
    logMessage("User is on Lease Calculator page.");
  }

  public void clickOnOperatingLeaseRadioButton() {
    switchToTheFirstFrame();
    click(element("radioBtn_operatingLease"));
    switchToDefaultFrame();
    logMessage("User clicked on operating leasing radio button.");
  }

  public void clickOnCapitalLeaseRadioButton() {
    switchToTheFirstFrame();
    click(element("radioBtn_capitalLease"));
    switchToDefaultFrame();
    logMessage("User clicked on capital leasing radio button.");
  }

  public void fillTheRequiredInputsAndVerifyMonthlyPaymentCalculation(
      String carPrice,
      String depositType,
      String deposit,
      String contractDuration,
      String interestRate,
      String residualValue,
      String monthlyPayment) {
    switchToTheFirstFrame();
    element("input_carPrice").clear();
    element("input_deposit").clear();
    enterText(element("input_carPrice"), carPrice);
    new Select(element("select_depositType")).selectByValue("1");
    enterText(element("input_deposit"), deposit);
    new Select(element("select_contractPeriod")).selectByVisibleText(contractDuration);
    enterText(element("input_interestRate"), interestRate);
    enterText(element("input_residualValue"), residualValue);

    Assert.assertEquals(
        element("label_monthlyPayment").getText(),
        monthlyPayment,
        "ASSERTION Failed: Monthly payment had been calculated incorrectly.");
    logMessage(
        "Test Case Passed: Expected: "
            + monthlyPayment
            + ", Actual: "
            + element("label_monthlyPayment").getText());
    switchToDefaultFrame();
  }

  public void clickOnAddToCompareButton() {
    switchToTheFirstFrame();
    click(element("button_addToCompare"));
    logMessage("User clicked on Add to compare button.");
    switchToDefaultFrame();
  }

  public void verifyNumberOfDataRowInComparisionTable(int rowCount) {
    switchToTheFirstFrame();
    Assert.assertTrue(
        elements("count_comparisionTableRow").size() == rowCount, "ASSERTION Failed.");
    logMessage("Test Case Passed.");
    switchToDefaultFrame();
  }
}
