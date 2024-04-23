package learnrestassured.testng;

import org.testng.annotations.*;

public class TestNG002
{

    @AfterMethod
    void demo6()
    {
        System.out.println("After method");
    }

    @BeforeMethod
    void demo4()
    {
        System.out.println("Before Method");
    }
    @BeforeSuite
    void demo1()
    {
        System.out.println("BeforeSuite");
    }

    @AfterClass
    void demo7()
    {
        System.out.println("After class");
    }

    @BeforeClass
    void demo3()
    {
        System.out.println("Before class");
    }

    @BeforeTest
    void demo2()
    {
        System.out.println("Before Test");
    }

    @Test
    void demo5()
    {
        System.out.println("test");
    }

    @AfterTest
    void demo8()
    {
        System.out.println("after test");
    }

    @AfterSuite
    void demo9()
    {
        System.out.println("After Suite");
    }
}
