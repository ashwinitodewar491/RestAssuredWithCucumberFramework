package resources.pojo;

public class CreateBookingResponse {
    private float bookingid;
    Booking BookingObject;


    // Getter Methods

    public float getBookingid() {
        return bookingid;
    }

    public Booking getBooking() {
        return BookingObject;
    }

    // Setter Methods

    public void setBookingid( float bookingid ) {
        this.bookingid = bookingid;
    }

    public void setBooking( Booking bookingObject ) {
        this.BookingObject = bookingObject;
    }
}

