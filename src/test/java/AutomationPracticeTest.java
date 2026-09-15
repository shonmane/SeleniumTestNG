import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.DropdownPage;
import pageobjects.RadioButtonPage;
import utility.BaseTest;
import utility.configreader.AppsConfig;
import utility.configreader.ConfigReader;
import utility.driver.DriverFactory;
import utility.enums.AppNames;

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
        System.out.println("Selected Radio Button");
    }

    @Test
    public void selectDropdownTest(){
        pages().dropdownPage().selectOptionDropdown();
        System.out.println("Selected all three dropdown options");
    }
}
