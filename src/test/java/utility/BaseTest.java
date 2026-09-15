package utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.Pages;
import utility.configreader.AppsConfig;
import utility.configreader.ConfigReader;
import utility.driver.DriverFactory;
import utility.driver.DriverManager;

public class BaseTest{

    private static final ThreadLocal<Pages> pages = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters( "env")
    public static void setUp(@Optional("qa") String env){
        ConfigReader.loadConfig(env);
        WebDriver driver = DriverFactory.createDriver(ConfigReader.getBrowser());
        DriverManager.setDriver(driver);
        pages.set(new Pages(driver));
    }

    @AfterMethod(alwaysRun = true)
    public static void cleanUp(){
        DriverManager.cleanup();
    }

    protected Pages pages(){
        return pages.get();
    }
}
