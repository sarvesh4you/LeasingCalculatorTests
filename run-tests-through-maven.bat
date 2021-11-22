REM enable you to include comments(remarks) in your batch file i.e., completely ignored by command-processor
REM This batch file executes TestNG Tests With Maven Build process


REM Below command configures and executes Test class
REM mvn clean verify -Dtest=

REM Below command runs the Suite TestNg xml file defined in pom.xml for Test execution
mvn clean verify