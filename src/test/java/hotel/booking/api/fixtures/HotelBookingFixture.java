package hotel.booking.api.fixtures;

import static hotel.booking.api.constants.BookingTestData.ADDITIONAL_NEEDS;
import static hotel.booking.api.constants.BookingTestData.CHECK_IN;
import static hotel.booking.api.constants.BookingTestData.CHECK_OUT;
import static hotel.booking.api.constants.BookingTestData.DEPOSITE_PAID;
import static hotel.booking.api.constants.BookingTestData.FIRSTNAME;
import static hotel.booking.api.constants.BookingTestData.LASTNAME;
import static hotel.booking.api.constants.BookingTestData.PASSWORD;
import static hotel.booking.api.constants.BookingTestData.TOTAL_PRICE;
import static hotel.booking.api.constants.BookingTestData.UPDATE_FIRSTNAME;
import static hotel.booking.api.constants.BookingTestData.UPDATE_LASTNAME;
import static hotel.booking.api.constants.BookingTestData.UPDATE_TOTAL_PRICE;
import static hotel.booking.api.constants.BookingTestData.USERNAME;

import hotel.booking.api.helpers.BookingHelper;
import hotel.booking.api.model.Booking;
import hotel.booking.api.model.Bookingdates;
import hotel.booking.api.model.TokenAuth;

public class HotelBookingFixture {
	
	protected Booking booking;
	protected Booking updateBooking;
	protected Booking partialUpdateBooking;
	protected TokenAuth tokenAuth;
	protected BookingHelper bookingHelper;

	public HotelBookingFixture() {
		init();
		createBooking();
		createTokenAuth();
		updateBooking();
		partialUpdateBooking();
	}

	private void init() {
		bookingHelper = new BookingHelper();
		booking = new Booking();
		updateBooking = new Booking();
		partialUpdateBooking = new Booking();
		tokenAuth = new TokenAuth();
	}
	
	private void createBooking() {
		booking.setFirstname(FIRSTNAME);
		booking.setLastname(LASTNAME);
		booking.setTotalprice(TOTAL_PRICE);
		booking.setDepositpaid(DEPOSITE_PAID);
		booking.setAdditionalneeds(ADDITIONAL_NEEDS);
		Bookingdates bookingDates = new Bookingdates();
		bookingDates.setCheckin(CHECK_IN);
		bookingDates.setCheckout(CHECK_OUT);
		booking.setBookingdates(bookingDates);
	}
	
	private void updateBooking() {
		updateBooking.setFirstname(UPDATE_FIRSTNAME);
		updateBooking.setLastname(UPDATE_LASTNAME);
		updateBooking.setTotalprice(UPDATE_TOTAL_PRICE);
		updateBooking.setDepositpaid(DEPOSITE_PAID);
		updateBooking.setAdditionalneeds(ADDITIONAL_NEEDS);
		Bookingdates bookingDates = new Bookingdates();
		bookingDates.setCheckin(CHECK_IN);
		bookingDates.setCheckout(CHECK_OUT);
		updateBooking.setBookingdates(bookingDates);
	}
	
	private void partialUpdateBooking() {
		partialUpdateBooking.setFirstname(UPDATE_FIRSTNAME);
		partialUpdateBooking.setLastname(UPDATE_LASTNAME);
	}
	
	private void createTokenAuth() {
		tokenAuth.setUsername(USERNAME);
		tokenAuth.setPassword(PASSWORD);
	}
	
}
