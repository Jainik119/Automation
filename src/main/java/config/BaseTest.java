package config;

import org.testng.annotations.*;

public class BaseTest {

    BaseTest(){}

    @BeforeSuite
    public void beforeSuite(){}

    @BeforeGroups
    public void beforeGroup(){}

    @BeforeTest
    public void beforeTest(){}

    @BeforeClass
    public void beforeClass(){}

    @BeforeMethod
    public void beforeMethod(){}


    @AfterMethod
    public void afterMethod(){}

    @AfterClass
    public void afterClass(){}

    @AfterTest
    public void afterTest(){}

    @AfterMethod
    public void afterGroup(){}

    @AfterSuite
    public void afterSuite(){}


}
