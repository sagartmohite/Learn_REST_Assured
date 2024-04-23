package learnrestassured.crud.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonBDDPATCH
{
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    String token = "360789e182faf84";


    @Test
    public void testPutREQUEST(){



        // url
        // auth - token , da829f2541bfd38 , headers - json
        // id - 3548
        // payload
        String payload = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";


        String payloadPATCH = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking/3068");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        //requestSpecification.body(payload).log().all();
        // Patch Request
        requestSpecification.body(payloadPATCH).log().all();


        // Calling PUT method on URI. After hitting we get Response
//        Response response = requestSpecification.when().put();

        Response response = requestSpecification.when().patch();


        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();


        validatableResponse.statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo("James"));
        validatableResponse.body("lastname", Matchers.equalTo("Brown"));

    }
}
