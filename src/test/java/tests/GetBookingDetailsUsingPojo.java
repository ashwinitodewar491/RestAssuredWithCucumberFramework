package tests;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.BaseClass;
import resources.pojo.CreateBookingRequest;
import static io.restassured.RestAssured.given;

public class GetBookingDetailsUsingPojo extends BaseClass {
    @BeforeClass
    public void initializeBaseUri() {
        createBaseUri("https://restful-booker.herokuapp.com/");
    }
    @Test(description = "Fetch booking details with pojo class object", groups = "E2E")
    public void fetchingGetBookingDetailsUsingPojo() {
        String response = given().body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}").header("Content-Type", "application/json").log().all().
                when().post("booking").
                then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        int bookingId = jsonPath.getInt("bookingid");
        //To get details of above booking creation
        CreateBookingRequest bookingDetails
                = given().header("Content-Type", "application/json").expect().defaultParser(Parser.JSON). //log().all(). will not support now
                when().get("booking/" + bookingId).
                then().log().all().assertThat().statusCode(200).extract().response().as(CreateBookingRequest.class);
        System.out.println(bookingDetails.getFirstname());

    }
}
