package Pages;


import Util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareersPage extends BasePage {
    private WebDriver driver;

    private String careersPageUrl = "https://useinsider.com/careers/";

    private By ourStoryHeader = By.xpath("//section[@data-id='efd8002']//div//div//div//h2");
    private By ourStorySection = By.xpath("//section[@data-id='efd8002']");
    private By findYourCallingHeader= By.xpath("//div[@data-id='b6c45b2']//div//a//h3");
    private By findYourCallingSection= By.xpath("//div[@data-id='b6c45b2']");


    private By lifeAtInsiderHeader = By.xpath("//section[@data-id='a8e7b90']//div[@data-id='21cea83']//div//h2");
    private By lifeAtInsiderSection= By.xpath("//section[@data-id='a8e7b90']");

    private By seeAllTeamsButton = By.linkText("See all teams");
    private By qualityAssuranceSection = By.xpath("//a//h3[.='Quality Assurance']");


    public CareersPage(){
        this.driver = Driver.getDriver();
    }

    public CareersPage openCareersPage(){
        openUrl(careersPageUrl);
        return new CareersPage();
    }


    public boolean isOurStorySectionExist(){
        return find(ourStorySection).isDisplayed();
    }

    public String getOurStoryHeader(){
        return find(ourStoryHeader).getText();
    }

    public boolean isFindYourCallingSectionExist(){
        return find(findYourCallingSection).isDisplayed();
    }

    public String getFindYourCallingHeader(){
        return find(findYourCallingHeader).getText();
    }

    public boolean isLifeAtInsiderSectionExist(){
        return find(lifeAtInsiderSection).isDisplayed();
    }

    public String getLifeAtInsiderHeader(){
        return find(lifeAtInsiderHeader).getText();
    }

    public void clickSeeAllTeamsButton(){
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(seeAllTeamsButton)).click();
        When used this code, I got ElementNotClickable Exception Element is not clickable at point (X,Y)
*/
        WebElement element = find(seeAllTeamsButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("arguments[0].click();", element);
        waitForVisibilityOf(qualityAssuranceSection);
    }

    public boolean isQualityAssuranceSectionOpened(){
        return find(qualityAssuranceSection).isDisplayed();
    }
    public QualityAssurancePage clickQualityAssurance(){
        WebElement QASection = find(qualityAssuranceSection);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", QASection);
        wait.until(ExpectedConditions.presenceOfElementLocated(qualityAssuranceSection));
        js.executeScript("arguments[0].click();", QASection);
        return new QualityAssurancePage();


    }


}
