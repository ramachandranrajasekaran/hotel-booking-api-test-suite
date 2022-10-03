package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.DELETE_BOOKING_RESPONSE;
import static hotel.booking.api.constants.BookingTestData.DELETE_BOOKING_FAILURE_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Token;

public class TestDeleteBooking extends HotelBookingFixture {
	
	public Token token;
	
	@BeforeEach
	public void beforeEach() {
		token = bookingHelper.getToken(tokenAuth);
	}
	
	@Test
	public void testDeleteBooking() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
		
		String deleteMessage = bookingHelper.deleteBooking(bookingId, token, true);
		assertEquals(DELETE_BOOKING_RESPONSE, deleteMessage);
	}

	@Test
	public void testDeleteBookingInvalidBookingId() {
		int bookingId = Integer.MIN_VALUE;

		String deleteMessage = bookingHelper.deleteBooking(bookingId, token, false);
		assertEquals(DELETE_BOOKING_FAILURE_RESPONSE, deleteMessage);
	}
}
