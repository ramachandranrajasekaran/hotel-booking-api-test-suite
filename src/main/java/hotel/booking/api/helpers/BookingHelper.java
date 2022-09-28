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
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingHelper {
	
	public BookingHelper() {
		RestAssured.baseURI = ConfigManager.getInstance().getProperty("baseUrl");
	}
	
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
	
	public Booking getBooking(int id) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.get(RestEndpoints.GET_BOOKING)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Get booking details based on ID response code");
		
		Type type = new TypeReference<Booking>() {}.getType();
		return response.as(type);
	}
	
	public BookingConfirmation createBooking(Booking booking) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.body(booking)
				.post(RestEndpoints.CREATE_BOOKING)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Create booking response code");
		
		Type type = new TypeReference<BookingConfirmation>(){}.getType();
		return response.as(type);
	}
	
	public Booking updateBooking(int id, Booking booking, Token token) {
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
		
		assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Update booking response code");
		
		return response.as(new TypeReference<Booking>(){}.getType());
	}
	
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

	public String deleteBooking(int id, Token token) {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.headers("Cookie", "token="+token.getToken())
				.delete(RestEndpoints.DELETE_BOOKING)
				.thenReturn();
		
		assertEquals(HttpStatus.SC_CREATED, response.getStatusCode(), "Delete booking response code");
		
		return response.getBody().asString();
	}
}
