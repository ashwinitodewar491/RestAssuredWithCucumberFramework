package resources.pojo;

public class CreateBookingRequest {
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        Bookingdates BookingdatesObject;
        private String additionalneeds;
        // Getter Methods
        public String getFirstname() {
            return firstname;
        }
        public String getLastname() {
            return lastname;
        }
        public int getTotalprice() {
            return totalprice;
        }
        public boolean getDepositpaid() {
            return depositpaid;
        }
        public Bookingdates getBookingdates() {
            return BookingdatesObject;
        }
        public String getAdditionalneeds() {
            return additionalneeds;
        }
        // Setter Methods
        public String setFirstname(String firstname ) {
            if(firstname!=null){
            this.firstname = firstname;
        }
            else {
            this.firstname="Ashwini";
            }
            return firstname;
        }

        public void setLastname( String lastname ) {
            if(lastname!=null){
                this.lastname = lastname;
            }
            else {
                this.lastname="Todewar";
            }
        }
        public void setTotalprice( int totalprice ) {
            if (totalprice!=0){
            this.totalprice = totalprice;}
            else {
                this.totalprice=111;
            }
        }
        public void setDepositpaid( boolean depositpaid ) {
            this.depositpaid = depositpaid;
        }
        public void setBookingdates( Bookingdates bookingdatesObject ) {
            this.BookingdatesObject = bookingdatesObject;
        }
        public void setAdditionalneeds( String additionalneeds ) {
            this.additionalneeds = additionalneeds;
        }
    }

