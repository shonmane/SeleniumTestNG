package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.BaseTest;
import utility.configreader.AppsConfig;
import utility.configreader.ConfigReader;
import utility.enums.AppNames;
import utility.reporting.ExtentManager;

import static utility.driver.DriverManager.getDriver;

public class EventHubTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    public static void initialise(){
        AppsConfig appsConfig = ConfigReader.getApp(AppNames.EVENTHUB);
        getDriver().get(appsConfig.url);
        Assert.assertEquals(getDriver().getTitle(), "EventHub — Discover & Book Events");
        ExtentManager.logStep("Page title is: "+getDriver().getTitle());
    }

    @Test
    public void loginTest(){
        pages().eventHubLoginPage().loginToEventHub();
        Assert.assertEquals(getDriver().getTitle(), "EventHub — Discover & Book Events");
        pages().eventHubHomePage().waitForHomePageLoad();
        ExtentManager.logStepWithScreenshot("Verify Homepage");
    }


}
