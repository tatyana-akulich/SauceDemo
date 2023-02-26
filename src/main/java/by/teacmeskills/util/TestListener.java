package by.teacmeskills.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult result) {
        ITestContext context = result.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.moveFile(file,
                        new File(System.getProperty("user.dir") + "/src/test/resources/screenshots" + result.getMethod().getMethodName() + ".png"));
            }
        } catch (NoSuchSessionException | IllegalStateException | IOException e) {
            System.out.println("Screenshot was not taken " + e.getLocalizedMessage());
        }
    }
}
