### SEB Vehicle Lease Calculator - Selenium Automation
---

#### System Requirement:

* JDK 1.8 or above

* Maven 3.5 or above

* Eclipse IDE or any other of choice in case there is need to update the script.

* For execution of scripts on Chrome, you need to have executable file for Chrome driver and paste them at
  location ```"\src\test\resources\drivers"``` in project folder.

* You can download this executable file from below links
    * Chrome: https://drive.google.com/file/d/0B4FqnK04LJRnNWZFOEE3Wjd4aFk/view


#### Execution Steps:

Please follow the instructions to execute the tests on local:

*Checkout the code from GIT Stash with provided link :
https://github.com/sarvesh4you/LeasingCalculatorTests.git

*Import project in Eclipse as a maven project

*Run the test
class ```"/src/test/java/com/seb/leasecalculator/tests/Verify_Operating_Leasing_Calculation_Test.java"``` as
TestNg

Or run the test
class ```"/src/test/java/com/seb/leasecalculator/tests/Verify_Operating_Leasing_Calculation_Test.java"``` with
maven configuration

Or run SEB_Tests.xml accordingly as Testng test.

- Location of xml : ```"\src\test\resources\testngxml\SEB_Tests.xml"```

Or according to the Test Scope use following command in cmd in project root directory - To Execute the all test suite
```"mvn clean verify"```

Or use batch files to execute desired suit which are avilable in project base directory.

#### Result Files:

*Check console after the test execution.

Or Test Execution Results will be stored in the following directory once the test has completed

    ```"/src/test-output/emailable-report.html"```
    ```"/src/target/surefire-reports/"```
