package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.enums.SelectDropdownBy;

import java.time.Duration;
import java.util.Objects;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected String getPageTitle(){
        return driver.getTitle();
    }

    protected void waitForPageLoad() {
        wait.until(webDriver ->
                Objects.equals(((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState"), "complete")
        );
    }

    protected WebElement waitAndFind(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitAndClick(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void waitAndType(By locator, String textToEnter){
        WebElement element = waitAndFind(locator);
        element.clear();
        element.sendKeys(textToEnter);
    }

    protected void selectDropdown(By locator, String value, SelectDropdownBy selectBy){
        WebElement dropdown = waitAndFind(locator);
        Select select = new Select(dropdown);
        switch(selectBy){
            case INDEX -> select.selectByIndex(Integer.parseInt(value));
            case VISIBLE_TEXT -> select.selectByVisibleText(value);
            case VALUE -> select.selectByValue(value);
            default -> throw new IllegalArgumentException("Unsupported Select By strategy: "+selectBy);
        }
    }

}
