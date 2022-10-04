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

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Booking;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Need to develop Booking APIs")
@Feature("Get Booking API")
@Owner("Ramachandran Rajasekaran")
public class TestGetBooking extends HotelBookingFixture {
	
	@Test
	@Story("User should be able to get the booking details based on the booking id")
	@Severity(SeverityLevel.BLOCKER)
	@Description("To verify the get booking by booking id")
	public void testGetBookingById() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
		
		Booking bookingFetched = bookingHelper.getBooking(bookingId, true);
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
	
	@Test
	@Story("User should be responded with error response when trying to fetch the booking details of invalid booking id")
	@Severity(SeverityLevel.NORMAL)
	@Description("To verify the get booking by invalid booking id")
	public void testGetBookingByInvalidId() {
		int bookingId = Integer.MIN_VALUE;
		bookingHelper.getBooking(bookingId, false);
	}

}
