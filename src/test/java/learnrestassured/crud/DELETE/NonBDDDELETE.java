package learnrestassured.crud.DELETE;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDDELETE
{
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    String token = "360789e182faf84";


    @Test
    public void testDELETEREQUEST()
    {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/229");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);



        // Calling PUT method on URI. After hitting we get Response
//        Response response = requestSpecification.when().put();

        Response response = requestSpecification.when().delete();


        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();


        validatableResponse.statusCode(201);
//        validatableResponse.body("firstname", Matchers.equalTo("Pramod"));
//        validatableResponse.body("lastname", Matchers.equalTo("Brown"));

    }
}
