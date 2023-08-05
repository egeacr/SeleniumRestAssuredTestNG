package Util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;

public class Driver {

    private static WebDriver driver;
    private String browser;


    public static WebDriver getDriver(){
        if (driver == null){
            String browser = System.getProperty("browser", "chrome");
            System.out.println("Browser type = " + browser );
            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().driverVersion("114.0.5735.90").setup();
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless=new");
                options.addArguments("--headless");
                options.addArguments("--disable-notifications");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            }
            else if (browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

        }
        return driver;
    }

    //@AfterSuite
    public static void quitDriver(){
        driver.quit();
        driver = null;
    }
}
