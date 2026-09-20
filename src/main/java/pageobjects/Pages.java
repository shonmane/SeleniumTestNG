package pageobjects;

import org.openqa.selenium.WebDriver;
import pageobjects.eventhub.EventHubHomePage;
import pageobjects.eventhub.EventHubLoginPage;
import pageobjects.practice.DropdownPage;
import pageobjects.practice.RadioButtonPage;

public class Pages {

    private final WebDriver driver;

    private RadioButtonPage radioButtonPage;
    private DropdownPage dropdownPage;
    private EventHubLoginPage eventHubLoginPage;
    private EventHubHomePage eventHubHomePage;

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

    public EventHubLoginPage eventHubLoginPage() {
        if(eventHubLoginPage==null){
            eventHubLoginPage=new EventHubLoginPage(driver);
        }
        return eventHubLoginPage;
    }

    public EventHubHomePage eventHubHomePage(){
        if (eventHubHomePage==null){
            eventHubHomePage = new EventHubHomePage(driver);
        }
        return eventHubHomePage;
    }

}
