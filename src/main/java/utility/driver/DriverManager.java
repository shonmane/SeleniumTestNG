package utility.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager(){}

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if(tlDriver.get()==null){
            throw new IllegalStateException("Driver is not initialised or already closed");
        }
        return tlDriver.get();
    }

    public static void setDriver(WebDriver driver){
        tlDriver.set(driver);
    }

    public static void cleanup(){
        if(tlDriver.get()!=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }

    }

}
