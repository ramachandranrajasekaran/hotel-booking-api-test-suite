package hotel.booking.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBooking {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    public CreateBooking(String firstName, String lastName, int totalPrice,
            boolean depositePaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositePaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public boolean isDepositePaid() {
        return depositPaid;
    }
    public void setDepositePaid(boolean depositePaid) {
        this.depositPaid = depositePaid;
    }
    public BookingDates getBookingDates() {
        return bookingDates;
    }
    public void setBookingDates(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }
    public String getAdditionalNeeds() {
        return additionalNeeds;
    }
    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }



}
