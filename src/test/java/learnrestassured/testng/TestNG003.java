package learnrestassured.testng;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class TestNG003
{
    // PUT Request
    // Before - Token, ID

    RequestSpecification rs;
    ValidatableResponse res;
    String token;

    @BeforeTest
    public void getToken()
    {
        rs=RestAssured.given();
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("auth");
        rs.contentType(ContentType.JSON);
        rs.body(payload);
        Response response=rs.post();
        res=response.then();

        // Rest Assured -> Matchers (Hamcrest) - 1-2% Times you will be using it
        res.body("token", Matchers.notNullValue());

        // TestNG assertion
        token = response.then().log().all().extract().path("token");
        Assert.assertNotNull(token); //

        // AssertJ
        assertThat(token).isNotNull().isNotBlank().isNotEmpty();
        System.out.println(token);
    }

    @Test
    void testNonBDDStylePUTReq()
    {
        String jsonString = "{\r\n" + "    \"firstname\" : \"Pramod\",\r\n" + "    \"lastname\" : \"Dutta\",\r\n"
                + "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
                + "        \"checkin\" : \"2018-01-01\",\r\n" + "        \"checkout\" : \"2019-01-01\"\r\n"
                + "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

        // Setting content type to specify format in which request payload will be sent.
        // ContentType is an ENUM.
        rs.contentType(ContentType.JSON);

        // Setting a cookie for authentication as per API documentation
        rs.cookie("token", token);

        //Adding URI
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("booking/133");

        // Adding body as string
        rs.body(jsonString);

        // Calling PUT method on URI. After hitting we get Response
        Response response=rs.put();

        // Printing Response as string
        System.out.println(response.asString());

        //// Get Validatable response to perform validation
        res=response.then().log().all();

        // Validate status code as 200
        res.statusCode(200);

        // Validate value of firstName is updated
        res.body("firstname", Matchers.equalTo("Pramod"));

        // Validate value of lastName is updated
        res.body("lastname", Matchers.equalTo("Dutta"));

    }
}
