package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButtonPage extends BasePage{

    private static final By radioBtn1 = new By.ByXPath("//input[@value='radio1']");

    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }

    public void selectRadioButton(){
        waitAndClick(radioBtn1);
    }

    public void verifyRadioButtonSelected(){

    }
}
