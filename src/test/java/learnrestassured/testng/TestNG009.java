package learnrestassured.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG009
{
    @Test
    void testCase01(){
        Assert.assertEquals(true,true,"passed");
        System.out.println("This will print");
    }
    @Test
    void testCase02(){
        Assert.assertEquals(true,false,"Failed");
        System.out.println("This will not print");
    }
}
