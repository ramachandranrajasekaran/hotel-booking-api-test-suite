package hotel.booking.api.tests;

import static hotel.booking.api.constants.BookingTestData.DELETE_BOOKING_FAILURE_RESPONSE;
import static hotel.booking.api.constants.BookingTestData.DELETE_BOOKING_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Token;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Need to develop Booking APIs")
@Feature("Delete Booking API")
@Owner("Ramachandran Rajasekaran")
public class TestDeleteBooking extends HotelBookingFixture {
	
	public Token token;
	
	@BeforeEach
	@Step("Test delete booking setup")
	public void beforeEach() {
		token = bookingHelper.getToken(tokenAuth);
	}
	
	@Test
	@Story("User should send request to delete the booking based on the booking id")
	@Severity(SeverityLevel.BLOCKER)
	@Description("To verify the delete booking API with valid booking id")
	public void testDeleteBooking() {
		int bookingId = bookingHelper.createBooking(booking, true).getBookingid();
		
		String deleteMessage = bookingHelper.deleteBooking(bookingId, token, true);
		assertEquals(DELETE_BOOKING_RESPONSE, deleteMessage);
	}

	@Test
	@Story("User should be responded with failure message when trying to delete the booking with invalid booking id")
	@Severity(SeverityLevel.MINOR)
	@Description("To verify the delete booking with invalid booking id")
	public void testDeleteBookingInvalidBookingId() {
		int bookingId = Integer.MIN_VALUE;

		String deleteMessage = bookingHelper.deleteBooking(bookingId, token, false);
		assertEquals(DELETE_BOOKING_FAILURE_RESPONSE, deleteMessage);
	}
}
