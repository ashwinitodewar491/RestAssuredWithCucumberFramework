package resources.pojo;

public class Booking {
    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    BookingdatesResponse BookingdatesObject;
    private String additionalneeds;


    // Getter Methods

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public boolean getDepositpaid() {
        return depositpaid;
    }

    public BookingdatesResponse getBookingdates() {
        return BookingdatesObject;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    // Setter Methods

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public void setBookingdates(BookingdatesResponse bookingdatesObject) {
        this.BookingdatesObject = bookingdatesObject;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
