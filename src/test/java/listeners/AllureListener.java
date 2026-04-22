package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

public class AllureListener extends TestListenerAdapter {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            attachScreenshot(driver);
        }
    }
    private void attachScreenshot(WebDriver driver) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {

            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}