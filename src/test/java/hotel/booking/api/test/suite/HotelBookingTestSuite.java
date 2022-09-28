package hotel.booking.api.test.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Hotel Booking Test Suite")
@SelectPackages("hotel.booking.api.tests")
public class HotelBookingTestSuite {
}
