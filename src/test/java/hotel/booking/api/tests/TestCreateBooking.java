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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import hotel.booking.api.model.BookingConfirmation;

public class TestCreateBooking extends HotelBookingFixture {
	
	@Test
	public void testCreateBooking() {
		BookingConfirmation bookingConfirmation = bookingHelper.createBooking(booking);
		
		assertAll(
				() -> assertNotNull(bookingConfirmation.getBookingid()),
				() -> assertEquals(FIRSTNAME, booking.getFirstname()),
				() -> assertEquals(LASTNAME, booking.getLastname()),
				() -> assertEquals(TOTAL_PRICE, booking.getTotalprice()),
				() -> assertEquals(DEPOSITE_PAID, booking.getDepositpaid()),
				() -> assertEquals(ADDITIONAL_NEEDS, booking.getAdditionalneeds()),
				() -> assertEquals(CHECK_IN, booking.getBookingdates().getCheckin()),
				() -> assertEquals(CHECK_OUT, booking.getBookingdates().getCheckout())
				);
	}

}
