package learnrestassured.testng;

import org.testng.annotations.*;

public class TestNG004
{
    @BeforeTest
    public void getToken()
    {
        System.out.println("1");
    }

    @BeforeTest
    public void getBookingID()
    {
        System.out.println("2");
    }

    @Test
    public void t3()
    {
        //token and booking id
        System.out.println("3");
    }

}
