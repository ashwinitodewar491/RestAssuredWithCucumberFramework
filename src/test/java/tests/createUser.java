package tests;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import resources.BaseClass;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class createUser extends BaseClass {
    @BeforeClass
            public void initializeBaseUri() {
        createBaseUri("https://reqres.in/");
    }
    @Test(description = "Basic Post request asserting status code and printing response body with request body in test case",groups = "regression")
    public void createUserPostRequest(){
       String response= given().body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}").header("Content-Type","application/json").
                when().post("/api/users").
                then().assertThat().statusCode(201).extract().response().asString();
       System.out.println(response);
    }
    @Test(description = "Post request with body provided in in test case and schema validation",groups = "regression")
    public void createUserPostRequest2(){
        File schema = new File(System.getProperty("user.dir")+"/schema.json");// To parse response and travel into it to validate
        String response= given().body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").header("Content-Type","application/json").
                when().post("/api/users").
                then().assertThat().statusCode(201).extract().response().asString();
        JsonPath jsonPath=new JsonPath(response);
        String name=jsonPath.getString("name");
    if(name.equalsIgnoreCase("morpheus")){
    System.out.println("User is created successfully");
        }
    }
    @Test(description = "Post request with body provided as external json file test case",groups = "regression")
    public void createUserPostRequest3() {
        File requestBody = new File(System.getProperty("user.dir") + "/src/main/java/resources/payloads/CreateUserRequestPayload.json");
        String response = given().body(requestBody).header("Content-Type", "application/json").
                when().post("/api/users").
                then().assertThat().statusCode(201).extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        String name = jsonPath.getString("name");
        String job = jsonPath.getString("job");
        if (name.equalsIgnoreCase("morpheus")) {
            System.out.println("Name in request body matches response body");
        }
        if (job.equalsIgnoreCase("leader")) {
            System.out.println("Job in request body matches response body");
        }
    }
        @Test(description = "Post request with body provided as external json file test case and dynamic data providing in test case",groups = "regression")
        public void createUserPostRequest4() throws IOException {
            File testFile = new File(System.getProperty("user.dir")+"/src/main/java/resources/payloads/test");
            String request= FileUtils.readFileToString(testFile).replace("${name}","Ashwini").replace("${job}","todewar");
            String response= given().body(request).header("Content-Type","application/json").
                    when().post("/api/users").
                    then().assertThat().statusCode(201).extract().response().asString();
            JsonPath jsonPath=new JsonPath(response);
            String name=jsonPath.getString("name");
            String job=jsonPath.getString("job");
            if(name.equalsIgnoreCase("Ashwini")){
                System.out.println("Name in request body matches response body");
            }
            if(job.equalsIgnoreCase("todewar")){
                System.out.println("Job in request body matches response body");
            }
    }

}
