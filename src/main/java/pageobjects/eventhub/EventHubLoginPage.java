package pageobjects.eventhub;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BasePage;
import utility.reporting.ExtentManager;

import java.util.List;

public class EventHubLoginPage extends BasePage {

    public EventHubLoginPage(WebDriver driver){
        super(driver);
    }

    private static final By emailTxtbox = new By.ById("email");
    private static final By passwordTxtbox = new By.ById("password");
    private static final By loginBtn = new By.ById("login-btn");

    public void loginToEventHub(){
        waitAndType(emailTxtbox, "abcd1234@gmail.com");
        waitAndType(passwordTxtbox, "Abcd@1234");
        waitAndClick(loginBtn);
        ExtentManager.logStepWithScreenshot("Logging in");

    }

    public void waitForLoginPageLoad(){
        waitForPageLoad();
    }

}
