## Payconiq Senior QA Engineer Tech Assignment 

### Tech Stack Used
Language: <b>Java</b></br>
Build Tool: <b>Maven</b></br>
Test Framework: <b>JUnit 5</b></br>
API Test Library: <b>RestAssured</b>

### Solution Description
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

Two ways of creating string json request,
1. Through POJO class
2. Through string substitution of json content read from json file

### Files
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

junit-platform.properties -- To execute JUnit test in parallel.

pom.xml -- To define the maven project, dependencies and plugins.

### Run Test Suite
#### To execute from maven
1. Clone the repository

	git clone https://github.com/ramachandranrajasekaran/hotel-booking-api-test-suite.git

2. Go to hotel-booking-api-test-suite directory

	cd hotel-booking-api-test-suite
	
3. Run the below command to trigger the test suite

	mvn clean surefire-report:report


#### Output
Below is the output after executing the test suite through maven,

	$ mvn clean surefire-report:report
	[INFO] Scanning for projects...
	[WARNING]
	[WARNING] Some problems were encountered while building the effective model for hotel.booking.api:hotel-booking-api-test-suite:jar:0.1.0-SNAPSHOT
	[WARNING] 'build.plugins.plugin.version' for org.apache.maven.plugins:maven-compiler-plugin is missing. @ line 75, column 20
	[WARNING]
	[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
	[WARNING]
	[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
	[WARNING]
	[INFO]
	[INFO] -----------< hotel.booking.api:hotel-booking-api-test-suite >-----------
	[INFO] Building Hotel Booking API Automation Test Suite 0.1.0-SNAPSHOT
	[INFO] --------------------------------[ jar ]---------------------------------
	[INFO]
	[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ hotel-booking-api-test-suite ---
	[INFO] Deleting E:\clone\payconiq\hotel-booking-api-test-suite\target
	[INFO]
	[INFO] >>> maven-surefire-report-plugin:3.0.0-M7:report (default-cli) > [surefire]test @ hotel-booking-api-test-suite >>>
	[INFO]
	[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ hotel-booking-api-test-suite ---
	[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
	[INFO] skip non existing resourceDirectory E:\clone\payconiq\hotel-booking-api-test-suite\src\main\resources
	[INFO]
	[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ hotel-booking-api-test-suite ---
	[INFO] Nothing to compile - all classes are up to date
	[INFO]
	[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ hotel-booking-api-test-suite ---
	[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
	[INFO] Copying 6 resources
	[INFO]
	[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ hotel-booking-api-test-suite ---
	[INFO] Changes detected - recompiling the module!
	[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
	[INFO] Compiling 7 source files to E:\clone\payconiq\hotel-booking-api-test-suite\target\test-classes
	[INFO]
	[INFO] --- maven-surefire-plugin:3.0.0-M7:test (default-test) @ hotel-booking-api-test-suite ---
	[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
	[INFO]
	[INFO] -------------------------------------------------------
	[INFO]  T E S T S
	[INFO] -------------------------------------------------------
	[INFO] Running hotel.booking.api.test.HotelBookingRestTest
	21:34:51.497 [ForkJoinPool-1-worker-1] INFO  hotel.booking.api.utils.ResourceUtil - Test properties read successfully
	21:34:51.502 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Before class setup done successfully
	21:34:51.794 [ForkJoinPool-1-worker-1] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:51.794 [ForkJoinPool-1-worker-4] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:51.794 [ForkJoinPool-1-worker-3] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:51.794 [ForkJoinPool-1-worker-2] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:52.859 [ForkJoinPool-1-worker-5] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:56.713 [ForkJoinPool-1-worker-2] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:56.714 [ForkJoinPool-1-worker-5] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:56.715 [ForkJoinPool-1-worker-3] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:56.715 [ForkJoinPool-1-worker-4] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:56.719 [ForkJoinPool-1-worker-5] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest verifyGetBookingByIdWithParams
	21:34:56.720 [ForkJoinPool-1-worker-4] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest createAndVerifyBookingWithNullFirstName
	21:34:56.720 [ForkJoinPool-1-worker-2] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest verifyDeleteBooking
	21:34:56.721 [ForkJoinPool-1-worker-3] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest verifyDeleteBookingWithInvalidBookingId
	21:34:56.761 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:56.763 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest getAndVerifyBookingById
	21:34:58.104 [ForkJoinPool-1-worker-3] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:58.413 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Execution completed - hotel.booking.api.test.HotelBookingRestTest getAndVerifyBookingById
	21:34:58.421 [ForkJoinPool-1-worker-1] INFO  hotel.booking.api.utils.ResourceUtil - Json file bearerToken.json read successfully
	21:34:59.455 [ForkJoinPool-1-worker-3] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:59.456 [ForkJoinPool-1-worker-3] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest createAndVerifyBooking
	21:34:59.764 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Auth token retrieved successfully
	21:34:59.765 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Started execution - hotel.booking.api.test.HotelBookingRestTest verifyPartialUpdateBooking
	21:34:59.853 [ForkJoinPool-1-worker-2] INFO  h.b.api.test.HotelBookingRestTest - Execution completed - hotel.booking.api.test.HotelBookingRestTest verifyDeleteBooking
	21:35:00.153 [ForkJoinPool-1-worker-5] INFO  h.b.api.test.HotelBookingRestTest - Execution completed - hotel.booking.api.test.HotelBookingRestTest verifyGetBookingByIdWithParams
	21:35:01.192 [ForkJoinPool-1-worker-3] INFO  h.b.api.test.HotelBookingRestTest - Execution completed - hotel.booking.api.test.HotelBookingRestTest createAndVerifyBooking
	21:35:01.382 [ForkJoinPool-1-worker-1] INFO  hotel.booking.api.utils.ResourceUtil - Json file partialUpdateBooking.json read successfully
	21:35:03.077 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Execution completed - hotel.booking.api.test.HotelBookingRestTest verifyPartialUpdateBooking
	21:35:03.081 [ForkJoinPool-1-worker-1] INFO  h.b.api.test.HotelBookingRestTest - Teared down test setup
	[ERROR] Tests run: 7, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 11.805 s <<< FAILURE! - in hotel.booking.api.test.HotelBookingRestTest
	[ERROR] hotel.booking.api.test.HotelBookingRestTest.verifyDeleteBookingWithInvalidBookingId  Time elapsed: 6.536 s  <<< FAILURE!
	org.opentest4j.AssertionFailedError: expected: <405> but was: <404>
	        at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
	        at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
	        at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
	        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
	        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
	        at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:527)
	        at hotel.booking.api.test.HotelBookingRestTest.verifyDeleteBookingWithInvalidBookingId(HotelBookingRestTest.java:232)
	        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
	        at java.base/java.lang.reflect.Method.invoke(Method.java:577)
	        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:217)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:213)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:138)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	        at org.junit.platform.engine.support.hierarchical.ForkJoinPoolHierarchicalTestExecutorService$ExclusiveTask.compute(ForkJoinPoolHierarchicalTestExecutorService.java:185)
	        at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
	        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1182)
	        at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1655)
	        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1622)
	        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:165)
	
	[ERROR] hotel.booking.api.test.HotelBookingRestTest.createAndVerifyBookingWithNullFirstName  Time elapsed: 6.869 s  <<< FAILURE!
	org.opentest4j.AssertionFailedError: expected: <500> but was: <400>
	        at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
	        at org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
	        at org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
	        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
	        at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
	        at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:527)
	        at hotel.booking.api.test.HotelBookingRestTest.createAndVerifyBookingWithNullFirstName(HotelBookingRestTest.java:214)
	        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
	        at java.base/java.lang.reflect.Method.invoke(Method.java:577)
	        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
	        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:147)
	        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:86)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(InterceptingExecutableInvoker.java:103)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.lambda$invoke$0(InterceptingExecutableInvoker.java:93)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
	        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:92)
	        at org.junit.jupiter.engine.execution.InterceptingExecutableInvoker.invoke(InterceptingExecutableInvoker.java:86)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$7(TestMethodTestDescriptor.java:217)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:213)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:138)
	        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:68)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:151)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	        at org.junit.platform.engine.support.hierarchical.ForkJoinPoolHierarchicalTestExecutorService$ExclusiveTask.compute(ForkJoinPoolHierarchicalTestExecutorService.java:185)
	        at java.base/java.util.concurrent.RecursiveAction.exec(RecursiveAction.java:194)
	        at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
	        at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1182)
	        at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1655)
	        at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1622)
	        at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:165)
	
	[INFO]
	[INFO] Results:
	[INFO]
	[ERROR] Failures:
	[ERROR]   HotelBookingRestTest.createAndVerifyBookingWithNullFirstName:214 expected: <500> but was: <400>
	[ERROR]   HotelBookingRestTest.verifyDeleteBookingWithInvalidBookingId:232 expected: <405> but was: <404>
	[INFO]
	[ERROR] Tests run: 7, Failures: 2, Errors: 0, Skipped: 0
	[INFO]
	[ERROR] There are test failures.
	
	Please refer to E:\clone\payconiq\hotel-booking-api-test-suite\target\surefire-reports for the individual test results.
	Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
	[INFO]
	[INFO] <<< maven-surefire-report-plugin:3.0.0-M7:report (default-cli) < [surefire]test @ hotel-booking-api-test-suite <<<
	[INFO]
	[INFO]
	[INFO] --- maven-surefire-report-plugin:3.0.0-M7:report (default-cli) @ hotel-booking-api-test-suite ---
	[WARNING] Unable to locate Test Source XRef to link to - DISABLED
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time:  18.797 s
	[INFO] Finished at: 2022-09-19T21:35:04+05:30
	[INFO] ------------------------------------------------------------------------


NOTE: The emailable report will be generate 'target/site/surefire-report.html'.