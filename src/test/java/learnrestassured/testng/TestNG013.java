package learnrestassured.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG013
{
    // Data Driven Testing -
    // Test - with multiple inputs
    // loginTest - > 100 of users
    // rEGSTIERATION - DDT

    // Data Provider - Test Data?
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                new Object[]{"admin", "admin"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password"},
                new Object[]{"admin", "password123"}
        };
    }

    @Test(dataProvider = "getData")
    public void loginTest(String username,String password)
    {
        System.out.println(username);
        System.out.println(password);
    }
}
