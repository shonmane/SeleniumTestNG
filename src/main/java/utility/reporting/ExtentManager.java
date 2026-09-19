package utility.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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
}