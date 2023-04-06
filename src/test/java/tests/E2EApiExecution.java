package tests;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.BaseClass;
import static io.restassured.RestAssured.given;
public class E2EApiExecution {

    public class createUser extends BaseClass {
        @BeforeClass
        public void initializeBaseUri() {
            createBaseUri("https://restful-booker.herokuapp.com/");
        }
        @Test(description = "Basic End to end flow(create, update,delete) asserting status code and printing response body with request body in test case",groups = "E2E")
        public void endToEndExecutionFromUserCreationToDeletion(){
            String response= given().body("{\n" +
                            "    \"firstname\" : \"Jim\",\n" +
                            "    \"lastname\" : \"Brown\",\n" +
                            "    \"totalprice\" : 111,\n" +
                            "    \"depositpaid\" : true,\n" +
                            "    \"bookingdates\" : {\n" +
                            "        \"checkin\" : \"2018-01-01\",\n" +
                            "        \"checkout\" : \"2019-01-01\"\n" +
                            "    },\n" +
                            "    \"additionalneeds\" : \"Breakfast\"\n" +
                            "}").header("Content-Type","application/json").log().all().
                    when().post("booking").
                    then().log().all().assertThat().statusCode(200).extract().response().asString();
            JsonPath jsonPath=new JsonPath(response);
            int bookingId=jsonPath.getInt("bookingid");

            //To get details of above booking creation
            String bookingDetails
                    = given().header("Content-Type","application/json").log().all().
                    when().get("booking/"+bookingId).
                    then().log().all().assertThat().statusCode(200).extract().response().asString();
            //To update booking details
            System.out.println("executing update details");
            String updateBookingDetails
                    = given().auth().preemptive().basic("admin","password123").header("Content-Type","application/json").cookie("abc123").body("{\n" +
                            "    \"firstname\" : \"James\",\n" +
                            "    \"lastname\" : \"Brown\",\n" +
                            "    \"totalprice\" : 111,\n" +
                            "    \"depositpaid\" : true,\n" +
                            "    \"bookingdates\" : {\n" +
                            "        \"checkin\" : \"2018-01-01\",\n" +
                            "        \"checkout\" : \"2019-01-01\"\n" +
                            "    },\n" +
                            "    \"additionalneeds\" : \"Breakfast\"\n" +
                            "}").log().all().
                    when().put("booking/"+bookingId).
                    then().log().all().assertThat().statusCode(200).extract().response().asString();
            System.out.println("executing update details done");
                //To delete Booking
            String deleteBooking=given().auth().preemptive().basic("admin","password123").header("Content-Type","application/json").cookie("abc123").log().all().
                    when().delete("booking/"+bookingId).
                    then().log().all().assertThat().statusCode(201).extract().response().asString();
            if(deleteBooking.equalsIgnoreCase("Created")){
                System.out.println("Booking is deleted");}
            else {
                System.out.println("Booking is not deleted");}
            //To verify if booking is deleted
            String deletionVerification=given().header("Content-Type","application/json").log().all().
                    when().get("booking/"+bookingId).
                    then().log().all().assertThat().statusCode(404).extract().response().asString();
                    if(deletionVerification.equalsIgnoreCase("Not Found")){
                        System.out.println("Booking is deleted");}
                    else {
                        System.out.println("Booking is not deleted");}
        }
    }
}
