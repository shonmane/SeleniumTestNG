package utility.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.driver.DriverManager;
import utility.reporting.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utility.reporting.ExtentManager.captureScreenshotAsBase64;
import static utility.reporting.ExtentManager.getTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = ExtentManager.getInstance()
                .createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = getTest();
        extentTest.log(Status.FAIL, result.getThrowable());

        String screenshotPath = captureScreenshotAsBase64();
        if (screenshotPath != null) {
            extentTest.addScreenCaptureFromBase64String(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush(); // writes the HTML file to disk
    }







}