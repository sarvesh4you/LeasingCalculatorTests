package com.seb.leaseCalculator.tests;

import com.seb.automation.TestSessionInitiator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.seb.automation.utils.PropertyFileReaderAndWriter.getPropertyFromTestData;

public class Verify_Operating_Leasing_Calculation_Test {

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
   * LeaseCalc Test - 1)Verify the calculation of monthly payment in case user select Operating
   * Leasing type
   */
  @Test(priority = 2, dependsOnMethods = "Step01_Launch_Lease_Calculator_Application")
  public void Step02_Verify_Operating_Leasing_Monthly_Payment_Calculation() {
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
  }

  /** LeaseCalc Test - Close the browser session */
  @AfterClass
  public void close_Test_Session() throws IOException {
     test.closeBrowserSession();
  }
}
