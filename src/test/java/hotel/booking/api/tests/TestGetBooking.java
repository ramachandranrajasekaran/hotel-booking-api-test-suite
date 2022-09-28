package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.ADDITIONAL_NEEDS;
import static hotel.booking.api.constants.BookingTestData.CHECK_IN;
import static hotel.booking.api.constants.BookingTestData.CHECK_OUT;
import static hotel.booking.api.constants.BookingTestData.DEPOSITE_PAID;
import static hotel.booking.api.constants.BookingTestData.FIRSTNAME;
import static hotel.booking.api.constants.BookingTestData.LASTNAME;
import static hotel.booking.api.constants.BookingTestData.TOTAL_PRICE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hotel.booking.api.model.Booking;

public class TestGetBooking extends HotelBookingFixture {
	
	@Test
	public void testGetAllBookingIds() {
		int bookingId = bookingHelper.createBooking(booking).getBookingid();
		
		Booking bookingFetched = bookingHelper.getBooking(bookingId);
		assertAll(
				() -> assertEquals(FIRSTNAME, bookingFetched.getFirstname()),
				() -> assertEquals(LASTNAME, bookingFetched.getLastname()),
				() -> assertEquals(TOTAL_PRICE, bookingFetched.getTotalprice()),
				() -> assertEquals(DEPOSITE_PAID, bookingFetched.getDepositpaid()),
				() -> assertEquals(ADDITIONAL_NEEDS, bookingFetched.getAdditionalneeds()),
				() -> assertEquals(CHECK_IN, bookingFetched.getBookingdates().getCheckin()),
				() -> assertEquals(CHECK_OUT, bookingFetched.getBookingdates().getCheckout())
				);
	}

}
