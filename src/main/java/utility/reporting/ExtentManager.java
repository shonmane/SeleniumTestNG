package utility.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utility.driver.DriverManager;

public final class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentManager() {}

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Regression Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void logStep(String message) {
        System.out.println(message);
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            currentTest.log(Status.INFO, message);
        }
        // if null, the console line above still went out — logging degrades gracefully
        // instead of crashing whatever setup code called it
    }

    public static String captureScreenshotAsBase64() {
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

    public static void logStepWithScreenshot(String message) {
        System.out.println(message);
        ExtentTest currentTest = getTest();
        if (currentTest == null) {
            return;
        }
        String base64 = captureScreenshotAsBase64();
        if (base64 != null) {
            currentTest.log(Status.INFO, message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        } else {
            currentTest.log(Status.INFO, message);
        }
    }
}