package Tests;

import Pages.CareersPage;
import Pages.HomePage;
import Pages.OpenQAJobsListPage;
import Pages.QualityAssurancePage;
import Util.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class openHomePage {
    private WebDriver driver;
    SoftAssert softassert = new SoftAssert();

    private HomePage homePage;
    private CareersPage careersPage;
    private OpenQAJobsListPage openQAJobsListPage;
    private QualityAssurancePage qualityAssurancePage;
    private String expectedHomePageTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
    private String expectedCareersPageOurStorySection = "Our story";
    private String expectedCareersPageFindYourCalling = "Find your calling";
    private String expectedLifeAtInsiderHeader = "Life at Insider";

    @BeforeClass
    public void setUp(){
        driver = Driver.getDriver();
        homePage = new HomePage();
        openQAJobsListPage = new OpenQAJobsListPage();
    }

    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
    }

    @Test
    public void openHomePage(){
        homePage.openPage();
        homePage.getPageTitle();
        Assert.assertEquals(homePage.getPageTitle(), expectedHomePageTitle,
                "Insider home page cannot open");
    }


    @Test(dependsOnMethods={"openHomePage"})
    public void openCareersPage(){
        careersPage = homePage.clickCareersLink();


        softassert.assertTrue(careersPage.isLifeAtInsiderSectionExist(),
                "Our Story Section cannot open");
        softassert.assertEquals(careersPage.getLifeAtInsiderHeader(), expectedLifeAtInsiderHeader,
                "Life At Insider header is false!");

        softassert.assertTrue(careersPage.isFindYourCallingSectionExist(),
                "Find your calling section cannot open");

        softassert.assertTrue(careersPage.isOurStorySectionExist(),
                "Our Story section cannot open");

        softassert.assertEquals(careersPage.getFindYourCallingHeader(), expectedCareersPageFindYourCalling);
        softassert.assertEquals(careersPage.getOurStoryHeader(), expectedCareersPageOurStorySection);
    }


    @Test(dependsOnMethods={"openCareersPage"})
    public void isOpenedSeeAllTeamsSection(){
       careersPage.clickSeeAllTeamsButton();
       Assert.assertTrue(careersPage.isQualityAssuranceSectionOpened(),
               "Quality Assurance section cannot open!");

    }

    @Test(dependsOnMethods = {"isOpenedSeeAllTeamsSection"})
    public void isOpenedQualityAssuranceJobsPage(){
        qualityAssurancePage= careersPage.clickQualityAssurance();
        Assert.assertEquals(qualityAssurancePage.getPageHeader(), "Quality Assurance",
                "Quality Assurance Page header is false!");
    }

    @Test(dependsOnMethods = {"isOpenedQualityAssuranceJobsPage"})
    public void isOpenQAJobsPageOpened(){
        openQAJobsListPage =qualityAssurancePage.clickSeeAllQAJobs();
        Assert.assertEquals(openQAJobsListPage.getFilterByDepartment(), "Quality Assurance");

    }

    @Test(dependsOnMethods ={"isOpenQAJobsPageOpened"})
    public void SelectLocationForJob(){
        openQAJobsListPage.selectTown();
        Assert.assertEquals(openQAJobsListPage.getSelectedTown(), "Istanbul, Turkey");
    }

    @Test(dependsOnMethods ={"SelectLocationForJob"})
    public void isThereOpenQAPosition(){
        Assert.assertTrue(openQAJobsListPage.getOpenPositionNumber(),
                "There is not any open positions at QA department");
    }

    @Test(dependsOnMethods = {"isThereOpenQAPosition"})
    public void checkOpenPositionsDepartment(){
        //openQAJobsListPage.openQAJobsListPage();
        List<String> actualDepartments = openQAJobsListPage.getOpenPositionsDepartment();
        for (int i =0; i<actualDepartments.size(); i++ ){
            Assert.assertEquals(actualDepartments.get(i),"Quality Assurance",
                    "Departments of all positions is not QA!");
        }

    }


    @Test(dependsOnMethods = {"checkOpenPositionsDepartment"})
    public void openJobDescriptionPage(){
        openQAJobsListPage.selectPosition(1);
    }








}
