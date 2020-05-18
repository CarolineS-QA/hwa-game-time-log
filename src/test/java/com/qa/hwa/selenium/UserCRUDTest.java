package com.qa.hwa.selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class UserCRUDTest {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void startReport(){
        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Caroline");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @Ignore
    @BeforeMethod
    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Ignore
    @Test
    public void CreateUserPageTest() throws InterruptedException, IOException {
        test = report.startTest("Verifying Navigation to Create User Page");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8181");
        test.log(LogStatus.INFO, "Navigating to the application web interface");
        WebElement createUserLink = driver.findElement(By.id("userCreation"));
        createUserLink.click();
        test.log(LogStatus.INFO, "Click on the userCreation link");
        sleep(2000);
        WebElement pageHeader = driver.findElement(By.id("pageHeader"));
        assertEquals(pageHeader.getText(), "GameTimeLog - Create a User");
        File userCreationPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(userCreationPic, new File(System.getProperty("user.dir") + "/test-output/userCreationPgae.jpg"));
        test.log(LogStatus.PASS, "'GameTimeLog - Create a User' is shown in the header", "<img src=userCreationPage.jpg>");
        sleep(2000);
    }

    @AfterMethod
    public void getResult(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test has failed " + result.getName());
            test.log(LogStatus.FAIL, "Test has failed " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed " + result.getName());
        }
        report.endTest(test);
    }

    @AfterTest
    public void endReport(){
        report.flush();
        report.close();
    }

}
