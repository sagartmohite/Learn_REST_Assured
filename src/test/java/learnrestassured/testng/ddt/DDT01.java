package learnrestassured.testng.ddt;

import org.testng.annotations.Test;

public class DDT01
{
    // Test Data - from the excel File - Data Provider
    // Read the Excel File
    // Apache POI, - 60-70% - little difficult to understand first time.
    // Fillo Library - 30% - Super Easy!
    
    // Test case  login where we will inject the username and password

    // Test case  login where we will inject the username and password

    // Excel -> There is not support direclty in Java to Read the Excel file
    // Apache POI library -
    // Excel - 2007 - xls, 2007 -> xlsx , CSV - comma seperated values
    //


    @Test(dataProvider="getData", dataProviderClass = UtilExcel.class)
    public void testLoginData(String username, String password)
    {
        System.out.println(username);
        System.out.println(password);
        System.out.println("---------");
    }

    // Login to app API


    // Write the code the Login POST request
    //

}
