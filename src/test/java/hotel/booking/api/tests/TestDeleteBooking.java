package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.DELETE_BOOKING_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hotel.booking.api.model.Token;

public class TestDeleteBooking extends HotelBookingFixture {
	
	@Test
	public void testGetAllBookingIds() {
		int bookingId = bookingHelper.createBooking(booking).getBookingid();
		Token token = bookingHelper.getToken(tokenAuth);
		
		String deleteMessage = bookingHelper.deleteBooking(bookingId, token);
		assertEquals(DELETE_BOOKING_RESPONSE, deleteMessage);
	}

}
