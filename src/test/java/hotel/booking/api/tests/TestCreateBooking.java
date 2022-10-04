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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.Booking;
import hotel.booking.api.model.BookingConfirmation;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Epic("Need to develop Booking APIs")
@Feature("Create Booking API")
@Owner("Ramachandran Rajasekaran")
@Severity(SeverityLevel.NORMAL)
public class TestCreateBooking extends HotelBookingFixture {

	@Story("User should be able to successfully create a booking on providing all the needed details")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	@Description("To verify the create booking with valid booking details")
	public void testCreateBooking() {
		BookingConfirmation bookingConfirmation = bookingHelper.createBooking(booking, true);
		
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

	@Stories({@Story("User should be able to successfully create booking on providing empty firstname"),
			@Story("User should be able to successfully create booking on providing space firstname"),
			@Story("User should be able to successfully create booking on providing tab firstname"),
			@Story("User should be able to successfully create booking on providing line break firstname"),
	})
	@Severity(SeverityLevel.MINOR)
	@ParameterizedTest
	@Description("To verify create booking with different first name values like empty, space, tab and line break")
	@EmptySource
	@ValueSource(strings = { " ", "   ", "\t", "\n" })
	public void testCreateBookingDifferentFirstNameValues(String firstName) {
		booking.setFirstname(firstName);
		BookingConfirmation bookingConfirmation = bookingHelper.createBooking(booking, true);
		
		assertAll(
				() -> assertNotNull(bookingConfirmation.getBookingid()),
				() -> assertEquals(firstName, booking.getFirstname()),
				() -> assertEquals(LASTNAME, booking.getLastname()),
				() -> assertEquals(TOTAL_PRICE, booking.getTotalprice()),
				() -> assertEquals(DEPOSITE_PAID, booking.getDepositpaid()),
				() -> assertEquals(ADDITIONAL_NEEDS, booking.getAdditionalneeds()),
				() -> assertEquals(CHECK_IN, booking.getBookingdates().getCheckin()),
				() -> assertEquals(CHECK_OUT, booking.getBookingdates().getCheckout())
				);
	}
	
	@Story("User should be able to successfully create booking on providing null firstname")
	@Severity(SeverityLevel.MINOR)
	@ParameterizedTest
	@Description("To verify create booking with null firstname value")
	@NullSource
	public void testCreateBookingEmptyFirstNameValues(String firstName) {
		booking.setFirstname(firstName);
		bookingHelper.createBooking(booking, false);
	}

	@Story("User should get error response when trying to book with insufficient details")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	@Description("To verify create booking with incomplete booking details")
	public void testCreateBookingfailure() {
		Booking failureBooking = new Booking();
		failureBooking.setFirstname("John");

		bookingHelper.createBooking(failureBooking, false);
	}
	
}
