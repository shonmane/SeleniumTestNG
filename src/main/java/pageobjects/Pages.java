package pageobjects;

import org.openqa.selenium.WebDriver;

public class Pages {

    private final WebDriver driver;

    private RadioButtonPage radioButtonPage;
    private DropdownPage dropdownPage;

    public Pages(WebDriver driver){
        this.driver=driver;
    }

    public RadioButtonPage radioButtonPage(){
        if(radioButtonPage==null){
            radioButtonPage= new RadioButtonPage(driver);
        }
        return radioButtonPage;
    }

    public DropdownPage dropdownPage(){
        if(dropdownPage==null){
            dropdownPage=new DropdownPage(driver);
        }
        return dropdownPage;
    }



}
