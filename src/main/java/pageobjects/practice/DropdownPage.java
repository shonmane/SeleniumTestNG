package pageobjects.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.BasePage;
import utility.enums.SelectDropdownBy;

public class DropdownPage extends BasePage {

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    private static final By optionDrpdwn = new By.ById("dropdown-class-example");

    public void selectOptionDropdown(){
        selectDropdown(optionDrpdwn, "Option1", SelectDropdownBy.VISIBLE_TEXT);
        selectDropdown(optionDrpdwn, "option2", SelectDropdownBy.VALUE);
        selectDropdown(optionDrpdwn, "3", SelectDropdownBy.INDEX);
    }

}
