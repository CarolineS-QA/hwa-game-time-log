package com.qa.hwa.selenium;

import com.qa.hwa.App;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
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

    @BeforeMethod
    public void setUp(){
        SpringApplication.run(App.class);
        driver = new ChromeDriver();
    }

    public void navigateToWebInterface(){
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8180/");
        test.log(LogStatus.INFO, "Navigating to the application web interface");
    }

    @Test
    public void createUserTest() throws InterruptedException, IOException {
        test = report.startTest("Verifying Navigation to Create User Page");
        navigateToWebInterface();
        sleep(5000);
        File webAppPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(webAppPic, new File(System.getProperty("user.dir") + "/test-output/webAppHome.jpg"));
        test.log(LogStatus.INFO, "Navigated to the web interface", "<img src=webAppHome.jpg>");
        sleep(2000);
        WebElement createUserLink = driver.findElement(By.id("userCreation"));
        createUserLink.click();
        test.log(LogStatus.INFO, "Click on the userCreation link");
        sleep(2000);
        WebElement pageHeader = driver.findElement(By.id("pageHeader"));
        assertEquals(pageHeader.getText(), "GameTimeLog - Create a User");
        File userCreationPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(userCreationPic, new File(System.getProperty("user.dir") + "/test-output/userCreationPage.jpg"));
        test.log(LogStatus.PASS, "'GameTimeLog - Create a User' is shown in the header", "<img src=userCreationPage.jpg>");
        sleep(2000);
        WebElement userInput = driver.findElement(By.name("username"));
        WebElement hoursInput = driver.findElement(By.name("freeTimeHours"));
        WebElement minsInput = driver.findElement(By.name("freeTimeMinutes"));
        WebElement button = driver.findElement(By.name("bSubmit"));
        userInput.sendKeys("Nick9000");
        hoursInput.sendKeys("15");
        minsInput.sendKeys("30");
        sleep(1000);
        button.click();
        test.log(LogStatus.INFO, "Click on the submit button");
        sleep(1000);
        driver.switchTo().alert().accept();
        test.log(LogStatus.INFO, "Accept the alert pop up.");
        WebElement userHeader = driver.findElement(By.id("pageHeader"));
        assertEquals(userHeader.getText(), "GameTimeLog - User Landing");
        File userCreateRedirectPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(userCreateRedirectPic, new File(System.getProperty("user.dir") + "/test-output/userLandingPage.jpg"));
        test.log(LogStatus.PASS, "'GameTimeLog - User Landing' is shown in the header", "<img src=userLandingPage.jpg>");
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
