package Pages;

import Util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(){
        this.driver=Driver.getDriver();
    }

    protected void openUrl(String url){
        driver.get(url);
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected  List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeout) {
        timeout = timeout != null ? timeout:Duration.ofSeconds(30);
        //if timeout value is null, default timeout is 30 secs.
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Duration... timeout) { //3 dots because it's optional
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeout.length>0 ? timeout[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    protected void click(By locator){
        waitForVisibilityOf(locator, Duration.ofSeconds(3));
        find(locator).click();
    }

    protected void type(By locator, String text){
        waitForVisibilityOf(locator, Duration.ofSeconds(3));
        find(locator).sendKeys(text);
    }

    protected String getText(By locator){
        waitForVisibilityOf(locator, Duration.ofSeconds(3));
        return find(locator).getText();
    }

}
