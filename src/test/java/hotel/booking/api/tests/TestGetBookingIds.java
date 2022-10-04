package hotel.booking.api.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import hotel.booking.api.fixtures.HotelBookingFixture;
import hotel.booking.api.model.BookingId;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Need to develop Booking APIs")
@Feature("Get All Booking Ids API")
@Owner("Ramachandran Rajasekaran")
public class TestGetBookingIds extends HotelBookingFixture {
	
	@Test
	@Story("User should be able to get all the booking ids from the system")
	@Severity(SeverityLevel.CRITICAL)
	@Description("To verify the get all booking ids")
	public void testGetAllBookingIds() {
		List<BookingId> bookingIds = bookingHelper.getBookingIds();
		assertNotNull(bookingIds, "Booking Id list is not empty");
		assertFalse(bookingIds.isEmpty(), "Booking Id list is not empty TRUE");
	}

}
