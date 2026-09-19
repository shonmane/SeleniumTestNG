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

    private static String captureScreenshotAsBase64() {
        try {
            WebDriver driver = DriverManager.getDriver();
            // getScreenshotAs(BASE64) returns the screenshot directly as a base64 String —
            // no file write, no disk path, no folder to keep alongside the report at all.
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (IllegalStateException e) {
            // driver already quit, or similar — don't let reporting crash the actual test result
            return null;
        }
    }

    public static void logStep(String message) {
        System.out.println(message);          // console — for live run visibility / CI console output
        getTest().log(Status.INFO, message);  // report — for the persisted HTML artifact
    }

    public static void logStepWithScreenshot(String message) {
        System.out.println(message);

        ExtentTest extentTest = getTest();
        extentTest.log(Status.INFO, message);
        extentTest.addScreenCaptureFromBase64String(captureScreenshotAsBase64());
    }


}