package config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    public WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

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
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/report/Report.html");
        // Create an object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Jainik");
        htmlReporter.config().setDocumentTitle("Title of the Report Comes here ");
        // Name of the report
        htmlReporter.config().setReportName("Name of the Report Comes here ");
        // Dark Theme
        htmlReporter.config().setTheme(Theme.DARK);
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }


    @AfterMethod
    public void afterMethod(ITestResult result)throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = getScreenShot(driver, result.getName());
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        driver.quit();
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest(){
        extent.flush();
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After Group");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }

    @Test
    public void verifyTitle() {
        logger = extent.createTest("To verify Google Title");
        Assert.assertEquals(driver.getTitle(),"Google");
    }

    @Test
    public void verifyLogo() {
        logger = extent.createTest("To verify Google Logo");
        boolean img = driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed();
        logger.createNode("Image is Present");
        Assert.assertTrue(img);
        logger.createNode("Image is not Present");
        Assert.assertFalse(img);
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\report\\screenshots\\" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}
