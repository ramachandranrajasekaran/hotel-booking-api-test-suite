package hotel.booking.api;

import static hotel.booking.api.HotelBookingRestConstants.BASE_URL;
import static hotel.booking.api.HotelBookingRestConstants.BEARER_TOKEN;
import static hotel.booking.api.HotelBookingRestConstants.CREATE_BOOKING;
import static hotel.booking.api.HotelBookingRestConstants.GET_BOOKING_IDS;
import static hotel.booking.api.HotelBookingRestConstants.PARTIAL_UPDATE_BOOKING;
import static hotel.booking.api.HotelBookingRestConstants.DELETE_BOOKING;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hotel.booking.api.pojo.BookingDates;
import hotel.booking.api.pojo.CreateBooking;
import hotel.booking.api.utils.ResourceUtil;
import hotel.booking.api.utils.RestRequestHelper;
import io.restassured.response.Response;

public class HotelBookingRestOperations {

    private static Logger logger = LoggerFactory.getLogger(HotelBookingRestOperations.class);

    Properties properties;

    public HotelBookingRestOperations(Properties properties) {
        this.properties = properties;
    }

    /**
     * The operation to get the bearer/auth token based on the username and password.
     * The bearerToken.json file is read from resources 'request' folder and the arguments
     * are substituted to generate the request body.
     * 
     * @param username to get the auth token
     * @param password to get the auth token
     * @return Response of the Rest Assured request
     */
    public Response getBearerToken(String username, String password) {
        String bearerTokenRequestBody = ResourceUtil.getRequest("bearerToken.json");
        bearerTokenRequestBody = String.format(bearerTokenRequestBody, username, password);

        logger.debug("Bearer Token Request URL: {}", getUrl(BEARER_TOKEN));
        logger.debug("Bearer Token Request Body: {}", bearerTokenRequestBody);
        return RestRequestHelper.postRequest(getUrl(BEARER_TOKEN), bearerTokenRequestBody);
    }

    /**
     * The operation to create a new booking. The booking details are generated from the POJO class.
     * 
     * @param firstName of the booking
     * @param lastName of the booking
     * @param totalPrice of the booking
     * @param depositPaid of the booking
     * @param checkIn time of the booking
     * @param checkOut time of the booking
     * @param additionalNeeds for the booking
     * @param bearerToken auth to create booking
     * @return Response of the Rest Assured request
     */
    public Response createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid,
            String checkIn, String checkOut, String additionalNeeds, String bearerToken) {
        logger.debug("CreateBooking URL: {}", getUrl(CREATE_BOOKING));
        BookingDates bookingDates = new BookingDates(checkIn, checkOut);
        CreateBooking createBooking = new CreateBooking(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
        String createBookingBody = ResourceUtil.getJsonString(createBooking);

        logger.debug("Create Booking Request Body: {}", createBookingBody);
        return RestRequestHelper.postRequest(getUrl(CREATE_BOOKING), createBookingBody, bearerToken);
    }

    /**
     * The operation to get all the booking id's from the application.
     * 
     * @return Response of the Rest Assured request
     */
    public Response getBookingIds() {
        logger.debug("CreateBooking URL: {}", getUrl(GET_BOOKING_IDS));
        return RestRequestHelper.getRequest(getUrl(GET_BOOKING_IDS));
    }

    /**
     * The operation to get the booking id's based on the params passed. The params are passed to
     * filter the booking id's.
     * 
     * @param params filtering conditions like based on firstname or lastname
     * @return Response of the Rest Assured request
     */
    public Response getBookingIds(Map<String, Object> params) {
        logger.debug("CreateBooking URL: {}", getUrl(GET_BOOKING_IDS));
        return RestRequestHelper.getRequest(getUrl(GET_BOOKING_IDS), params);
    }

    /**
     * The operation to update partial update booking. The request body is generated from the
     * partialUpdateBooking.json file read from resources 'request' folder and substitute the
     * needed values.
     * 
     * @param bookingId
     * @param firstName
     * @param lastName
     * @param bearerToken
     * @return Response of the Rest Assured request
     */
    public Response partialUpdateBooking(Integer bookingId, String firstName, String lastName, String bearerToken) {
        String url = getUrl(PARTIAL_UPDATE_BOOKING) + bookingId.toString();
        logger.debug("Partial Update Booking URL {}", url);

        String updateBookingRequestBody = ResourceUtil.getRequest("partialUpdateBooking.json");
        updateBookingRequestBody = String.format(updateBookingRequestBody, firstName, lastName);

        return RestRequestHelper.patchRequest(url, updateBookingRequestBody, bearerToken);
    }

    /**
     * The operation to delete booking based on the bookingId.
     * 
     * @param bookingId the booking id to be deleted
     * @param bearerToken auth required for successful operation
     * @return Response of the Rest Assured request
     */
    public Response deleteBooking(Integer bookingId, String bearerToken) {
        String url = getUrl(DELETE_BOOKING) + bookingId.toString();
        logger.debug("Partial Update Booking URL {}", url);

        return RestRequestHelper.deleteRequest(url, bearerToken);
    }

    /**
     * This method concatenates the base url with the uri passed as param
     * 
     * @param urlPath the URI (path) for performing User Management Operation
     * @return the URL
     */
    private String getUrl(String urlPath) {
        return properties.getProperty(BASE_URL) + properties.getProperty(urlPath);
    }

}
