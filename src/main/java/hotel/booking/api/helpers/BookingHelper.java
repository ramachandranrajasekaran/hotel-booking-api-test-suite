package hotel.booking.api.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;

import hotel.booking.api.constants.RestEndpoints;
import hotel.booking.api.model.Booking;
import hotel.booking.api.model.BookingConfirmation;
import hotel.booking.api.model.BookingId;
import hotel.booking.api.model.Token;
import hotel.booking.api.model.TokenAuth;
import hotel.booking.api.utils.ConfigManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingHelper {
	
	public BookingHelper() {
		RestAssured.baseURI = ConfigManager.getInstance().getProperty("baseUrl");
	}

	@Step("Send request to get authentication token")
	@Attachment
	public Token getToken(TokenAuth tokenAuth) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body(tokenAuth)
				.post(RestEndpoints.CREATE_TOKEN)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Get auth token response code");
		
		return response.as(new TypeReference<Token>(){}.getType());
	}

	@Step("Send get booking ids request")
	@Attachment
	public List<BookingId> getBookingIds() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get(RestEndpoints.GET_BOOKING_ID)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Get booking response code");
		
		Type type = new TypeReference<List<BookingId>>(){}.getType();
		
		List<BookingId> bookingIds = response.as(type);
		return bookingIds;
	}

	@Step("Sent get booking details request")
	@Attachment
	public Booking getBooking(int id, boolean isInvalidBookingId) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.get(RestEndpoints.GET_BOOKING)
				.thenReturn();
		
		if(!isInvalidBookingId) {
			assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode(), "Get booking by invalid booking id response code");
			return null;	
		}
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Get booking details based on ID response code");
		return response.as(new TypeReference<Booking>() {}.getType());
		
	}

	@Step("Send create booking POST request")
	@Attachment
	public BookingConfirmation createBooking(Booking booking, boolean isValidRequest) {
		Response response = RestAssured
				.given()
				.log().all(true)
				.contentType(ContentType.JSON)
				.body(booking)
				.post(RestEndpoints.CREATE_BOOKING)
				.thenReturn();

		if(!isValidRequest) {
			assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusCode(), "Create booking failure response code");
			return null;
		}

		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Create booking response code");
		return response.as(new TypeReference<BookingConfirmation>(){}.getType());		
		
	}

	@Step("Send update booking details request")
	@Attachment
	public Booking updateBooking(int id, Booking booking, Token token, boolean isValidRequestData) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.headers("Cookie", "token="+token.getToken())
				.accept(ContentType.JSON.getContentTypeStrings()[0])
				.body(booking)
				.put(RestEndpoints.UPDATE_BOOKING)
				.thenReturn();
		
		if(!isValidRequestData) {
			assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode(), "Update booking bad request data response code");
			return null;
		}
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Update booking response code");
		return response.as(new TypeReference<Booking>(){}.getType());
	}

	@Step("Send partial update booking request")
	@Attachment
	public Booking partialUpdateBooking(int id, Booking booking, Token token) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.headers("Cookie", "token="+token.getToken())
				.accept(ContentType.JSON.getContentTypeStrings()[0])
				.body(booking)
				.patch(RestEndpoints.PARTIAL_UPDATE_BOOKING)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Partial update booking response code");
		
		return response.as(new TypeReference<Booking>(){}.getType());
	}

	@Step("Send delete booking request")
	@Attachment
	public String deleteBooking(int id, Token token, boolean isValidBookingId) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.headers("Cookie", "token="+token.getToken())
				.delete(RestEndpoints.DELETE_BOOKING)
				.thenReturn();
		
		if(isValidBookingId) {
			assertEquals(HttpStatus.SC_CREATED, response.getStatusCode(), "Delete booking response code");
		} else {
			assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.getStatusCode(), "Delete booking invalid booking id response code");
		}
				
		return response.getBody().asString();
	}
}
