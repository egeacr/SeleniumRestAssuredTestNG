package Pages;

import Util.Driver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private WebDriver driver;

    public HomePage(){
        this.driver=Driver.getDriver();
    }




    private By cookieAccept = By.id("wt-cli-accept-all-btn");
    private By company = By.xpath("//ul[@class='navbar-nav']/li[position()=5]");

    private By parentDiv = By.className("new-menu-dropdown-layout-6-mid-container");

    // Find the "Careers" link within the parent div
    private By careersLink = By.linkText("Careers");

    private String pageUrl = "https://useinsider.com/";


    public CareersPage clickCareersLink(){
        click(cookieAccept);
        waitForVisibilityOf(company);
        click(company);
        waitForVisibilityOf(careersLink);
        click(careersLink);
        return new CareersPage();
    }



    public void openPage(){
        driver.get(pageUrl);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

}
