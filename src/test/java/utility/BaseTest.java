package utility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.Pages;
import utility.configreader.AppsConfig;
import utility.configreader.ConfigReader;
import utility.driver.DriverFactory;
import utility.driver.DriverManager;
import utility.listeners.TestListener;

@Listeners(TestListener.class)
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
