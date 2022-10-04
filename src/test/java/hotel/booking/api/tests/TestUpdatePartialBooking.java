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

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Booking;
import hotel.booking.api.model.Token;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Need to develop Booking APIs")
@Feature("Partial Update Booking API")
@Owner("Ramachandran Rajasekaran")
public class TestUpdatePartialBooking extends HotelBookingFixture {
	
	@Test
	@Story("User should be able to partially update the booking based on the partial booking details sent")
	@Severity(SeverityLevel.CRITICAL)
	@Description("To verify the partial update with valid booking data")
	public void testUpdatePartialBooking() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
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
