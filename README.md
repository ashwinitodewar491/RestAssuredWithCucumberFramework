# RestAssuredWithCucumberFramework

Rest Assured Framework Step by Step using cucumber/BDD and Java language

Introduction:
Rest Assured is an open-source and easy-to-use Java DSL used by developers and automation engineers to test rest services. Rest assured, the framework also provides the flexibility to write BDD-style scripts to enhance readability. Integrating rest assured into a ci/cd pipeline is easy.
Prerequisites:
Eclipse/Intellij should be available
Create Maven Project:maven-archetype-quickstart from apache

Maven dependencies to be added in the POM file:

<dependencies>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>org.hamcrest</groupId>
        <artifactId>hamcrest</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>json-schema-validator</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version><!-- Latest version --></version>
    </dependency>
</dependencies>


Please Make sure to replace <!-- Latest version --> with the latest versions of the respective dependencies.

Basic CRUD Examples:
We'll provide examples of basic CRUD operations using Rest Assured. You can find all the APIs documented in the Postman Collection Document

Usage of Basic Functions
Here are some basic functions and keywords used while testing APIs with Rest Assured:
Given: Input details such as path parameters, query parameters, headers, and request body.
When: Method used (GET, PUT, POST, PATCH, DELETE, UPDATE) and resource URL.
Then: Assertions and validations.
Keywords
extract(): Extracts API response details once the API is triggered,
assertThat().statusCode(): Asserts the status code.
response(): Fetches the response.
asString(): Converts JSON response to a string.
auth(): Adds authentication if required.
basic(): Provides basic authentication with username and password.
JsonPath: Used to extract JSON response for validation.

Implementation with Rest Assured:
We'll demonstrate how to implement basic CRUD operations with Rest Assured using Java.
a) Method: GET ( Get method is used to fetch details without impacting the database, it is also called a readOnly Operation .Here we are using it to fetch the booking details ) 
public void getSingleBookingDetailsRequest()
{
RestAssured.baseURI="https://restful-booker.herokuapp.com";
String bookingDetails=given().header("Content-Type","application/json").log().all().
                    		when().get("booking/5285).
                    		then().log().all().assertThat().statusCode(200).
extract().response().asString();
       }
b) Method: POST  (Post Method generates a new entry in the database with a unique ID , here we are using it to create new booking in different ways)
Request Body in the Test case itself by creating a variable
public void createBookingPostRequest()
{
RestAssured.baseURI="https://restful-booker.herokuapp.com";
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
                    	then().log().all().assertThat().statusCode(200).
extract().response().asString();
}
Sending hardcoded static request body in the request itself
public void createUserPostRequest()
{
RestAssured.baseURI="https://restful-booker.herokuapp.com";
String response=given().body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}").header("Content-Type","application/json").
                	when().post("/api/users").
                	then().assertThat().statusCode(201).
extract().response().asString();
       		System.out.println(response);
    }
Sending Static JSON file in the request body:
public void createUserPostRequest3()
{
File requestBody = new File(System.getProperty("user.dir") + "/src/main/java/resources/payloads/CreateUserRequestPayload.json");
 String response = 	given().body(requestBody).header("Content-Type", "application/json").
               		when().post("/api/users").
                		then().assertThat().statusCode(201).
extract().response().asString();
}
Sending text file with a variable declared which we will be replacing: 


public void createUserPostRequest4() throws IOException 
{
File testFile = new File(System.getProperty("user.dir")+"/src/main/java/resources/payloads/test");
String request= FileUtils.readFileToString(testFile).replace("${name}","Ashwini").replace("${job}","todewar");
String response= 	given().body(request).header("Content-Type","application/json").
                    		when().post("/api/users").
                    		then().assertThat().statusCode(201).extract().response().asString();
}

We have explored various methods for sending JSON in a POST request (refer to the above section on the POST method):

Sending the request body directly within the test case.
Storing static JSON in a file and sending it as part of the request.
Utilizing dynamic JSON with variables inserted for each field. Upon retrieval, these variables are converted into strings and their values are replaced accordingly.
Organizing payloads within a class and invoking a function that returns the JSON body

c) Method: PUT (This method is used to update existing data in the database, where we will be using it to update booking details )
public void updateBookingPutRequest(){
RestAssured.baseURI="https://restful-booker.herokuapp.com";
String updateBookingDetails =given().auth().preemptive()
.basic("admin","password123").header("Content-Type","applicatio      n/json").cookie("abc123").body("{\n" +
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
then().log().all().assertThat().statusCode(200).
extract().response().asString();
}
d) Method: DELETE (This method is used to delete data from a database, where we will be using it to delete booking) 
public void deleteBookingRequest()
{
RestAssured.baseURI="https://restful-booker.herokuapp.com";
String deleteBooking=given().auth().preemptive()
.basic("admin","password123").header("Content-Type","application/json").cookie("abc123").log().all().
when().delete("booking/"+bookingId)
.then().log().all().assertThat().statusCode(201).
extract().response().asString();
}
e) End to End execution for the above APIs:
This is also called API chaining.
API chaining involves creating a sequence of API calls, where the output of one API request serves as the input for the next. This technique facilitates the execution of a multi-step process in an automated and streamlined manner.
   
public void endToEndExecutionFromUserCreationToDeletion()
{
RestAssured.baseURI="https://restful-booker.herokuapp.com";

 To create a booking ( User is creating a new booking in this API )
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
                    	then().log().all().assertThat().statusCode(200).
extract().response().asString();
JsonPath jsonPath=new JsonPath(response);
            //Parsing Booking Id to fetch details with GET API
            int bookingId=jsonPath.getInt("bookingid");

To get details of the above booking creation ( Booking Id from above POST api call is getting used in fetching newly created booking ID and check Successful creation of booking )
String bookingDetails = given().header("Content-Type","application/json").log().all().
                    		when().get("booking/"+bookingId).
                    		then().log().all().assertThat().statusCode(200).
extract().response().asString();
To update booking details (The same booking ID is getting used to change the existing content using PUT method )
String updateBookingDetails =given().auth().preemptive().
basic("admin","password123").header("Content-Type","application/json").cookie("abc123").body("{\n" +
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
            then().log().all().assertThat().statusCode(200).
extract().response().asString();

To delete the Booking (Same Booking ID is getting used to delete booking )
String deleteBooking=given().auth().preemptive().
basic("admin","password123").header("Content-Type","application/json").cookie("abc123").log().all().
             when().delete("booking/"+bookingId).
             then().log().all().assertThat().statusCode(201)
.extract().response().asString();
if(deleteBooking.equalsIgnoreCase("Created"))
{
                System.out.println("Booking is deleted");}
            else {
                System.out.println("Booking is not deleted");
}

To verify if the booking has been deleted
String deletionVerification=given().header("Content-Type","application/json").log().all().
                    			when().get("booking/"+bookingId).
                   		 then().log().all().assertThat().statusCode(404).
extract().response().asString();
if(deletionVerification.equalsIgnoreCase("Not Found"))
{
                        System.out.println("Booking is deleted");}
                    else {
                        System.out.println("Booking is not deleted");}
  }
}
Test Run Result:


Git Repo URL:
https://github.com/ashwinitodewar491/RestAssuredWithCucumberFramework
References:
Fetch dependencies from the following link
https://mvnrepository.com/
We can use https://jsonpathfinder.com/ to cross-verify the path of the JSON response while adding an assertion.


Sample API testing sites:
1) https://reqres.in/
2) https://restful-booker.herokuapp.com/apidoc/index.html



