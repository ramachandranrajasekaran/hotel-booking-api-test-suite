package hotel.booking.api.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "bookingid", "booking" })
@Generated("jsonschema2pojo")
public class BookingConfirmation {

	@JsonProperty("bookingid")
	private Integer bookingid;
	@JsonProperty("booking")
	private Booking booking;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("bookingid")
	public Integer getBookingid() {
		return bookingid;
	}

	@JsonProperty("bookingid")
	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	@JsonProperty("booking")
	public Booking getBooking() {
		return booking;
	}

	@JsonProperty("booking")
	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "BookingConfirmation [bookingid=" + bookingid + ", booking=" + booking + "]";
	}
}