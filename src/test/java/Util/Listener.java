package Util;

import Pages.BasePage;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends BasePage implements ITestListener  {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() +" test failed!");
        try{
            captureScreenShot(result.getName());
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test start:" +result.getName());
    }
}
