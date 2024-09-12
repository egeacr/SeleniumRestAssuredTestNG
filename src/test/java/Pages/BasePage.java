package Pages;

import Util.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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
        waitForVisibilityOf(locator);
        return driver.findElements(locator);
    }


    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeout) {
        timeout = timeout != null ? timeout:Duration.ofSeconds(30);
        //if timeout value is null, default timeout is 30 secs.
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }


    protected void waitForVisibilityOf(By locator, Duration... timeout) {
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

    public void captureScreenShot(String methodName){
        try{
            File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("./Screenshots"+methodName+".png"));
        }
        catch (Exception e){
            e.getMessage();
        }
    }

}
