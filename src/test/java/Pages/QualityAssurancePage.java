package Pages;

import Util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QualityAssurancePage extends BasePage{

    private WebDriver driver;
    private By pageHeader = By.xpath("//section[@id='page-head']//h1");

    private By seeAllQAButton = By.linkText("See all QA jobs");

    public QualityAssurancePage(){
        this.driver = Driver.getDriver();
    }

    public String getPageHeader(){
        return find(pageHeader).getText();
    }

    public OpenQAJobsListPage clickSeeAllQAJobs(){
        click(seeAllQAButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return new OpenQAJobsListPage();
    }



}
