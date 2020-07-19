package config;

import org.testng.annotations.*;

public class BaseTest {

    BaseTest(){}

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @BeforeGroups
    public void beforeGroup(){
        System.out.println("Before Group");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }


    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After Group");
    }

    @AfterSuite
    public void afterSuite(){}


}
