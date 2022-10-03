package hotel.booking.api.test.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import io.qameta.allure.Epic;

@Suite
@Epic("Hotel Booking Test Suite")
@SuiteDisplayName("Hotel Booking Test Suite")
@SelectPackages("hotel.booking.api.tests")
public class HotelBookingTestSuite {
}
