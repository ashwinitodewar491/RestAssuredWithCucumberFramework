package resources.pojo;

public class Bookingdates {
    private String checkin;
    private String checkout;


    // Getter Methods

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    // Setter Methods

    public void setCheckin(String checkin) {
        if (checkin!=null) {
            this.checkin = checkin;
        }
        else { this.checkin = "2018-01-01";
        }
    }

    public void setCheckout(String checkout) {
        if (checkout!=null){
        this.checkout = checkout;}
    else{
        this.checkout="2019-01-01";
        }
    }
}
