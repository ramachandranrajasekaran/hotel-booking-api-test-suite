package hotel.booking.api.constants;

import hotel.booking.api.utils.ConfigManager;

public class BookingTestData {
	
	public static final String FIRSTNAME = ConfigManager.getInstance().getProperty("firstname");
	public static final String LASTNAME = ConfigManager.getInstance().getProperty("lastname");
	public static final int TOTAL_PRICE = Integer.parseInt(ConfigManager.getInstance().getProperty("totalPrice"));
	public static final boolean DEPOSITE_PAID = Boolean.parseBoolean(ConfigManager.getInstance().getProperty("depositePaid"));
	public static final String ADDITIONAL_NEEDS = ConfigManager.getInstance().getProperty("additionalNeeds");
	public static final String CHECK_IN = ConfigManager.getInstance().getProperty("checkIn");
	public static final String CHECK_OUT = ConfigManager.getInstance().getProperty("checkOut");
	
	public static final String USERNAME = ConfigManager.getInstance().getProperty("username");
	public static final String PASSWORD = ConfigManager.getInstance().getProperty("password");
	
	public static final String UPDATE_FIRSTNAME = ConfigManager.getInstance().getProperty("updateFirstname");
	public static final String UPDATE_LASTNAME = ConfigManager.getInstance().getProperty("updateLastname");
	public static final int UPDATE_TOTAL_PRICE = Integer.parseInt(ConfigManager.getInstance().getProperty("updateTotalPrice"));
	
	public static final String DELETE_BOOKING_RESPONSE = "Created";
	public static final String DELETE_BOOKING_FAILURE_RESPONSE = "Method Not Allowed";

}
