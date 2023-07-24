package Pages;

import Util.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenQAJobsListPage extends BasePage {

    private WebDriver driver;

    private By QAJobsPageIndicator = By.xpath("//span[@id='select2-filter-by-department-container']");
    private String url = "https://useinsider.com/careers/open-positions/?department=qualityassurance";

    private By townDropdown = By.id("select2-filter-by-location-container");
    private By selectLocation = By.id("filter-by-location");

    private By openPositions = By.xpath("//div[@data-team='qualityassurance']");
    private By openPositionsDepartment = By.xpath("//div[@data-team='qualityassurance']//span");
    private By viewRoleButton = By.xpath("//div[@data-team='qualityassurance']//a");
    private By cookeeAcceptButton = By.id ("wt-cli-accept-all-btn");



    public OpenQAJobsListPage(){
        this.driver= Driver.getDriver();
    }

    public void openQAJobsListPage(){
        driver.get(url);
        click(cookeeAcceptButton);
    }

    public void waitUntilPageLoaded(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");
    }

    public String getFilterByDepartment(){
        WebElement departmentDropDown = find(QAJobsPageIndicator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlContains("department=qualityassurance"));
        String a = departmentDropDown.getAttribute("title");
        System.out.println(a);
        return a;
    }

    public void selectTown(){
        WebElement dropdown = find(selectLocation);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Istanbul, Turkey");
    }

    public String getSelectedTown(){
        WebElement townDD = find(townDropdown);
        return townDD.getAttribute("title");
    }

    public boolean getOpenPositionNumber(){
        List <WebElement> positions = findAll(openPositions);
        System.out.println(positions.size());
        if(positions.size()>0)
            return true;
        else
            return false;
    }


    public List<String> getOpenPositionsDepartment(){
        List <WebElement> openPositions = findAll(openPositionsDepartment);
        JavascriptExecutor j = (JavascriptExecutor)driver;
        if (j.executeScript
                ("return document.readyState").toString().equals("complete"));

        List <String> list = new ArrayList<>();
        waitForVisibilityOf(openPositionsDepartment);
        for(int i =0; i<openPositions.size(); i++) {
            list.add(openPositions.get(i).getText());
        }
        return list;
    }

    public void selectPosition(int index){
        List<WebElement> allViewRoles = findAll(viewRoleButton);
        WebElement SecondElement = allViewRoles.get(index);
        Actions actions = new Actions(driver);
        actions.moveToElement(SecondElement).build().perform();
        SecondElement.click();
        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));

    }
}
