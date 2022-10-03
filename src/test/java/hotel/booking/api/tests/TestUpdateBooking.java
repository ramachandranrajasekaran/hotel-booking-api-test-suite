package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.ADDITIONAL_NEEDS;
import static hotel.booking.api.constants.BookingTestData.CHECK_IN;
import static hotel.booking.api.constants.BookingTestData.CHECK_OUT;
import static hotel.booking.api.constants.BookingTestData.DEPOSITE_PAID;
import static hotel.booking.api.constants.BookingTestData.UPDATE_FIRSTNAME;
import static hotel.booking.api.constants.BookingTestData.UPDATE_LASTNAME;
import static hotel.booking.api.constants.BookingTestData.UPDATE_TOTAL_PRICE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Booking;
import hotel.booking.api.model.Token;

public class TestUpdateBooking extends HotelBookingFixture {
	
	@Test
	public void testUpdateBooking() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
		Token token = bookingHelper.getToken(tokenAuth);
		
		Booking bookingFetched = bookingHelper.updateBooking(bookingId, updateBooking, token, true);
		assertAll(
				() -> assertEquals(UPDATE_FIRSTNAME, bookingFetched.getFirstname()),
				() -> assertEquals(UPDATE_LASTNAME, bookingFetched.getLastname()),
				() -> assertEquals(UPDATE_TOTAL_PRICE, bookingFetched.getTotalprice()),
				() -> assertEquals(DEPOSITE_PAID, bookingFetched.getDepositpaid()),
				() -> assertEquals(ADDITIONAL_NEEDS, bookingFetched.getAdditionalneeds()),
				() -> assertEquals(CHECK_IN, bookingFetched.getBookingdates().getCheckin()),
				() -> assertEquals(CHECK_OUT, bookingFetched.getBookingdates().getCheckout())
				);
	}
	
	@Test
	public void testUpdateBookingWithInvalidData() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
		Token token = bookingHelper.getToken(tokenAuth);
		Booking invalidUpdateBooking = new Booking();
		bookingHelper.updateBooking(bookingId, invalidUpdateBooking, token, false);
	}

}
