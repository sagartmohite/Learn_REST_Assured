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

import static org.assertj.core.api.Assertions.assertThat;

public class TestNG005
{
    String token;
    Integer bookingId;
    RequestSpecification rs;
    ValidatableResponse res;

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
    public void getBookingID()
    {
        System.out.println(" -  getBooking ID - ");

        rs = RestAssured.given();
        String payload = "{\n" +
                "    \"firstname\" : \"Amit\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking");
        rs.contentType(ContentType.JSON);
        rs.body(payload);

        // Making Req
        Response response = rs.when().post();

        //Validation Part
        ValidatableResponse validatableResponse =  response.then();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse.statusCode(200);

        bookingId = response.then().log().all().extract().path("bookingid");
        System.out.println(bookingId);
    }

    @Test
    public void testPUTRequest2() {

        System.out.println(" - Test Case PUT Request ");

        String payload = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Gill\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";




        rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com/");
        rs.basePath("booking/"+bookingId);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);

        //requestSpecification.body(payload).log().all();
        // Patch Request
        System.out.println(" - Before sending PUT Request ");
        rs.body(payload).log().all();


        // Calling PUT method on URI. After hitting we get Response
//        Response response = requestSpecification.when().put();

        Response response = rs.when().put();

        System.out.println(" - After sending PUT Request ");
        // Get Validatable response to perform validation
        res = response.then().log().all();


        res.statusCode(200);

        String firstName = response.then().log().all().extract().path("firstname");

        // AssertJ
        assertThat(firstName).isNotNull().isNotBlank().isNotEmpty();

    }
}
