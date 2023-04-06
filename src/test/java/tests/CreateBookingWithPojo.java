package tests;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.BaseClass;
import resources.pojo.Bookingdates;
import resources.pojo.CreateBookingRequest;
import resources.pojo.CreateBookingResponse;

import static io.restassured.RestAssured.given;

public class CreateBookingWithPojo extends BaseClass {
    @BeforeClass
    public void initializeBaseUri() {
        createBaseUri("https://restful-booker.herokuapp.com/");
    }

    @Test(description = "Create booking request with pojo and assert response with pojo classes", groups = "Pojo")
    public void createBookingRequestWithPojo() {
        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        CreateBookingRequest requestPayload=new CreateBookingRequest();
        requestPayload.setFirstname(null);
        requestPayload.setLastname("Todewar");
        requestPayload.setTotalprice(111);
        requestPayload.setDepositpaid(true);
        requestPayload.setBookingdates(bookingdates);
        requestPayload.setAdditionalneeds("Breakfast");

        CreateBookingResponse createBookingResponse = given().body(requestPayload).
                header("Content-Type", "application/json").
                expect().defaultParser(Parser.JSON).
                when().post("booking").
                then().log().all().assertThat().statusCode(200).extract().response().as(CreateBookingResponse.class);
        System.out.println(createBookingResponse.getBooking().getFirstname());
        System.out.println(createBookingResponse.getBooking().getLastname());

    }
}