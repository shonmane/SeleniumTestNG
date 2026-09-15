package utility.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DriverFactory {

    DriverFactory(){}

    public static WebDriver createDriver(String browserName){

       WebDriver driver = null;
       switch(browserName.toLowerCase()){
           case "chrome"-> driver = new ChromeDriver(getChromeOptions());
           case "firefox"-> driver = new FirefoxDriver();
           default -> throw new IllegalArgumentException("Provided browserName is not correct");
       }

       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));

        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
