## Payconiq Senior QA Engineer Tech Assignment 

### Tech Stack Used
Language: <b>Java</b></br>
Build Tool: <b>Maven</b></br>
Test Framework: <b>JUnit 5</b></br>
API Test Library: <b>RestAssured</b>

#### Solution Description
The test suite is developed on top of JUnit 5 framework.</br>
* It supports advanced annotations
* Parallel execution is possible
* It can support reporting tool integration
* Can be integrated with Jenkins (CI/CD pipeline)

Maven is choose as building tool since it is popular and contains all the needed dependency in its central repository. It is also easy to trigger
the JUnit Test and add plugins.

RestAssured is a powerful tool for testing REST API and it has in-build tool for extracting the value and asserting the response.

Logging functionality implemented. The test case execution will be printed on the console. Using logging it will be easy to debug the test suite.

All the test cases are independent and can be executed in parallel. There is no dependency on the test cases or its execution order.

#### Files
test.properties -- Maintained the configurable parts like baseURL, API operations path and bearer token. If there are any change in URL, path or
bearer token, the value can be changed only in the properties file and there will not be any change needed at other places.

requests folder --  Contains the json files which are the request body content of each API operation.

logback-test.xml -- Test logback file for logging functionality. By default, the log level is set to "info".

ResourceReader.java -- Util to read the files from resources folder. Read test.properties and json files.

RestRequestHelper.java -- Uses rest-assure to send the GET and POST requests and returns the response. For this project, only POST request is
sufficient, but as a test suite it should support multiple request types.

HotelBookingRestConstants.java -- Placeholder to define all the constants.

HotelBookingRestOperations.java -- Contains methods to perform different API operations.

HotelBookingRestTest.java -- Test class containing all the test cases.

BookingDates.java -- Pojo class to hold checkIn and checkOut values.

CreateBooking.java -- Pojo class to hold booking details.

pom.xml -- To define the maven project, dependencies and plugins.

