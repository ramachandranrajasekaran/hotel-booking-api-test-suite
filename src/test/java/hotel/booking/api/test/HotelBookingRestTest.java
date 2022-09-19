package hotel.booking.api.test;

import static hotel.booking.api.HotelBookingRestConstants.PASSWORD;
import static hotel.booking.api.HotelBookingRestConstants.USERNAME;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hotel.booking.api.HotelBookingRestOperations;
import hotel.booking.api.utils.ResourceUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Automation Test for Hotel Booking API
 */
@Execution(ExecutionMode.CONCURRENT)
@TestInstance(Lifecycle.PER_CLASS)
public class HotelBookingRestTest {

	private static Logger logger = LoggerFactory.getLogger(HotelBookingRestTest.class);

	private Properties properties;
	private HotelBookingRestOperations operations;
	private String bearerToken;

	/**
	 * Read the test properties. Initialize the HotelBookingRestOperations. Get
	 * BearerToken and set the variable for future requests. Verify the get bearer
	 * token request is successful.
	 */
	@BeforeAll
	public void setup() {
		properties = ResourceUtil.getProperties();
		operations = new HotelBookingRestOperations(properties);
		logger.info("Before class setup done successfully");
	}

	@AfterAll
	public void tearDown() {
		properties = null;
		operations = null;
		bearerToken = null;
		logger.info("Teared down test setup");
	}

	@BeforeEach
	public void setAuthToken() {
		Response response = operations.getBearerToken(properties.getProperty(USERNAME),
				properties.getProperty(PASSWORD));
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		bearerToken = responseBody.get("token");
		logger.info("Auth token retrieved successfully");
	}

	@Test
	@DisplayName("Test to verify the successful Create Booking")
	public void createAndVerifyBooking() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Response response = operations.createBooking("Dan", "Brown", 111, true, "2019-01-01", "2020-01-01", "Breakfast",
				bearerToken);
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		if (logger.isDebugEnabled()) {
			logger.debug("responseBody : {}", responseBody.prettyPrint());
		}
		
		assertAll(() -> assertNotNull(responseBody.get("bookingid")),
				() -> assertEquals("Dan", responseBody.get("booking.firstname")),
				() -> assertEquals("Brown", responseBody.get("booking.lastname")),
				() -> assertEquals((Integer) 111, responseBody.get("booking.totalprice")),
				() -> assertEquals(true, responseBody.get("booking.depositpaid")),
				() -> assertEquals("2019-01-01", responseBody.get("booking.bookingdates.checkin")),
				() -> assertEquals("2020-01-01", responseBody.get("booking.bookingdates.checkout")),
				() -> assertEquals("Breakfast", responseBody.get("booking.additionalneeds")));

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	@DisplayName("Test to verify the Get Booking Ids to list all booking id's")
	public void getAndVerifyBookingById() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Response response = operations.getBookingIds();
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		if(logger.isDebugEnabled()) {
			logger.debug("responseBody : {}", responseBody.prettyPrint());
		}
		assertNotNull(responseBody);

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	@DisplayName("Test to verify the successful Partial Update Booking")
	public void verifyPartialUpdateBooking() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Response response = operations.createBooking("Dan", "Brown", 111, true, "2019-01-01", "2020-01-01", "Breakfast",
				bearerToken);
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		Integer bookingId = (Integer) responseBody.get("bookingid");

		Response partialUpdateBookingResponse = operations.partialUpdateBooking(bookingId, "James", "Bond",
				bearerToken);
		assertEquals(partialUpdateBookingResponse.statusCode(), 200);
		if (logger.isDebugEnabled()) {
			logger.debug("partialUpdateBookingResponse: {}", partialUpdateBookingResponse.asPrettyString());
		}
		JsonPath partialUpdateBookingResponseBody = partialUpdateBookingResponse.jsonPath();

		assertAll(() -> assertEquals("James", partialUpdateBookingResponseBody.get("firstname")),
				() -> assertEquals("Bond", partialUpdateBookingResponseBody.get("lastname")),
				() -> assertEquals((Integer) 111, partialUpdateBookingResponseBody.get("totalprice")),
				() -> assertEquals(true, partialUpdateBookingResponseBody.get("depositpaid")),
				() -> assertEquals("2019-01-01", partialUpdateBookingResponseBody.get("bookingdates.checkin")),
				() -> assertEquals("2020-01-01", partialUpdateBookingResponseBody.get("bookingdates.checkout")),
				() -> assertEquals("Breakfast", partialUpdateBookingResponseBody.get("additionalneeds")));

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	@DisplayName("Test to verify the successful Delete Booking")
	public void verifyDeleteBooking() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Response response = operations.createBooking("Dan", "Brown", 111, true, "2019-01-01", "2020-01-01", "Breakfast",
				bearerToken);
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		Integer bookingId = (Integer) responseBody.get("bookingid");

		Response deleteBookingResponse = operations.deleteBooking(bookingId, bearerToken);
		assertEquals(deleteBookingResponse.statusCode(), 201);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBookingResponse: {}", deleteBookingResponse.asPrettyString());
		}
		assertEquals("Created", deleteBookingResponse.asPrettyString());

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	@DisplayName("Test to verify the get booking ids with params filtering")
	public void verifyGetBookingByIdWithParams() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		String firstName = UUID.randomUUID().toString();
		String lastName = UUID.randomUUID().toString();

		Response response = operations.createBooking(firstName, lastName, 111, true, "2019-01-01", "2020-01-01",
				"Breakfast", bearerToken);
		assertEquals(response.statusCode(), 200);
		JsonPath responseBody = response.jsonPath();
		Integer bookingId = (Integer) responseBody.get("bookingid");

		Map<String, Object> params = new HashMap<>();
		params.put("firstname", firstName);
		params.put("lastname", lastName);

		Response getBookingIdResponse = operations.getBookingIds(params);
		assertEquals(getBookingIdResponse.statusCode(), 200);
		if (logger.isDebugEnabled()) {
			logger.debug("getBookingIdResponse: {}", getBookingIdResponse.asPrettyString());
		}

		JsonPath getBookingResponseBody = response.jsonPath();
		assertEquals(bookingId, getBookingResponseBody.get("bookingid"));

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Test
	@DisplayName("Test to verify the failure case when firstname is null in Create Booking")
	public void createAndVerifyBookingWithNullFirstName() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Response response = operations.createBooking(null, "Brown", 111, true, "2019-01-01", "2020-01-01", "Breakfast",
				bearerToken);
		assertEquals(response.statusCode(), 400);
		if (logger.isDebugEnabled()) {
			logger.debug("responseBody : {}", response.prettyPrint());
		}
		assertEquals("Bad Request", response.asPrettyString());

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	@DisplayName("Test to verify the failed Delete Booking when booking id is not in the record")
	public void verifyDeleteBookingWithInvalidBookingId() {
		logger.info("Started execution - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());

		Integer bookingId = 98768789;
		Response deleteBookingResponse = operations.deleteBooking(bookingId, bearerToken);
		assertEquals(deleteBookingResponse.statusCode(), 404);
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBookingResponse: {}", deleteBookingResponse.asPrettyString());
		}
		assertEquals("Not Found", deleteBookingResponse.asPrettyString());

		logger.info("Execution completed - {} {}", this.getClass().getName(),
				Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}
