package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.ADDITIONAL_NEEDS;
import static hotel.booking.api.constants.BookingTestData.CHECK_IN;
import static hotel.booking.api.constants.BookingTestData.CHECK_OUT;
import static hotel.booking.api.constants.BookingTestData.DEPOSITE_PAID;
import static hotel.booking.api.constants.BookingTestData.TOTAL_PRICE;
import static hotel.booking.api.constants.BookingTestData.UPDATE_FIRSTNAME;
import static hotel.booking.api.constants.BookingTestData.UPDATE_LASTNAME;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hotel.booking.api.model.Booking;
import hotel.booking.api.model.Token;

public class TestUpdatePartialBooking extends HotelBookingFixture {
	
	@Test
	public void testGetAllBookingIds() {
		int bookingId = bookingHelper.createBooking(booking).getBookingid();
		Token token = bookingHelper.getToken(tokenAuth);
		
		Booking bookingFetched = bookingHelper.partialUpdateBooking(bookingId, partialUpdateBooking, token);
		assertAll(
				() -> assertEquals(UPDATE_FIRSTNAME, bookingFetched.getFirstname()),
				() -> assertEquals(UPDATE_LASTNAME, bookingFetched.getLastname()),
				() -> assertEquals(TOTAL_PRICE, bookingFetched.getTotalprice()),
				() -> assertEquals(DEPOSITE_PAID, bookingFetched.getDepositpaid()),
				() -> assertEquals(ADDITIONAL_NEEDS, bookingFetched.getAdditionalneeds()),
				() -> assertEquals(CHECK_IN, bookingFetched.getBookingdates().getCheckin()),
				() -> assertEquals(CHECK_OUT, bookingFetched.getBookingdates().getCheckout())
				);
	}

}
