package com.seb.leaseCalculator.tests;

import com.seb.automation.TestSessionInitiator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.seb.automation.utils.PropertyFileReaderAndWriter.getPropertyFromTestData;

public class Verify_Add_To_Compare_Button_Functionality_Test {

  TestSessionInitiator test;

  @BeforeClass
  public void Start_Test_Session() {
    test = new TestSessionInitiator();
  }

  @BeforeMethod
  public void handleTestMethodName(Method method) {
    test.stepStartMessage(method.getName());
  }

  /** LeaseCalc Test - Open browser and verify user is able to launch the application */
  @Test(priority = 1)
  public void Step01_Launch_Lease_Calculator_Application() {

    test.launchApplication(getPropertyFromTestData("baseUrl"));
  }

  /**
   * LeaseCalc Test - 2)Verify that user is able to add and compare two or more leasing queries after clicking on “Add to compare” button
   */
  @Test(priority = 2, dependsOnMethods = "Step01_Launch_Lease_Calculator_Application")
  public void Step02_Verify_Add_To_Compare_Button_Functionality() {
    test.leaseCalcPage.verifyUserIsOnLeaseCalcPage();
    test.leaseCalcPage.clickOnOperatingLeaseRadioButton();

    test.leaseCalcPage.fillTheRequiredInputsAndVerifyMonthlyPaymentCalculation(
        getPropertyFromTestData("carPrice1"),
        getPropertyFromTestData("depositType1"),
        getPropertyFromTestData("deposit1"),
        getPropertyFromTestData("contractPeriod1"),
        getPropertyFromTestData("interestRate1"),
        getPropertyFromTestData("residualValue1"),
        getPropertyFromTestData("monthlyPayment1"));
    test.leaseCalcPage.clickOnAddToCompareButton();

    test.leaseCalcPage.clickOnCapitalLeaseRadioButton();
    test.leaseCalcPage.fillTheRequiredInputsAndVerifyMonthlyPaymentCalculation(
            getPropertyFromTestData("carPrice2"),
            getPropertyFromTestData("depositType2"),
            getPropertyFromTestData("deposit2"),
            getPropertyFromTestData("contractPeriod2"),
            getPropertyFromTestData("interestRate2"),
            getPropertyFromTestData("residualValue2"),
            getPropertyFromTestData("monthlyPayment2"));
    test.leaseCalcPage.clickOnAddToCompareButton();

    test.leaseCalcPage.verifyNumberOfDataRowInComparisionTable(2);

  }

  /** LeaseCalc Test - Close the browser session */
  @AfterClass
  public void close_Test_Session() throws IOException {
     test.closeBrowserSession();
  }
}
