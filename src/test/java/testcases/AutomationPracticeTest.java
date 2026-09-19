package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.BaseTest;
import utility.configreader.AppsConfig;
import utility.configreader.ConfigReader;
import utility.enums.AppNames;
import utility.reporting.ExtentManager;

import static utility.driver.DriverManager.*;


public class AutomationPracticeTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    public static void initialise(){
        AppsConfig appsConfig = ConfigReader.getApp(AppNames.RS_AUTOMATION);
        getDriver().get(appsConfig.url);
        System.out.println("Page title is: "+getDriver().getTitle());
    }

    @Test
    public void radioButtonTest() {
        pages().radioButtonPage().selectRadioButton();
        ExtentManager.logStep("Selected Radio Button");
    }

    @Test
    public void selectDropdownTest(){
        pages().dropdownPage().selectOptionDropdown();
//        System.out.println("Selected all three dropdown options");
        ExtentManager.logStep("Selected all three dropdown options");
    }

    @Test
    public void failTest(){
        Assert.fail();
        System.out.println("Failure test");
    }
}
