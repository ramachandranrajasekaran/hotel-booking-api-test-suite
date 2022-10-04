## HOTEL BOOKING REST API TEST SUITE 


### TECH STACK
Language: <b>Java</b></br>
Build Tool: <b>Maven</b></br>
Test Framework: <b>JUnit 5</b></br>
API Test Library: <b>RestAssured</b></br>
Reporting: <b>Allure</b>


### TEST SUITE EXECUTION AND REPORTING

To run the test suite

	mvn clean test

To get the allure report as web server

	mvn allure:serve

To get HTML allure report

	mvn allure:report


#### REPORT
The HTML report --> target/site/allure-maven-plugin/index.html


### FEATURES
1. Uses RestAssure to send request
2. Serialization and deserialization of the model object done using Jackson fastxml
3. RestAssure internally serialize and deserialize the model objects
4. Junit parallel execution of the tests
5. Allure report generated
 