package pageobjects.eventhub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePage;

public class EventHubHomePage extends BasePage {

    public EventHubHomePage(WebDriver driver){
        super(driver);
    }

    private static final By homeTab = new By.ById("nav-home");

    public void waitForHomePageLoad(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeTab));
    }




}
