package hotel.booking.api.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.BookingId;

public class TestGetBookingIds extends HotelBookingFixture {
	
	@Test
	public void testGetAllBookingIds() {
		List<BookingId> bookingIds = bookingHelper.getBookingIds();
		assertNotNull(bookingIds, "Booking Id list is not empty");
		assertFalse(bookingIds.isEmpty(), "Booking Id list is not empty TRUE");
	}

}
