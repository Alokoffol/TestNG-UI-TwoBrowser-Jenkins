package base;

import constants.AppConstants;
import driver.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import listeners.AllureListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

@Listeners(AllureListener.class)
public class TestBase {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.createDriver(browser);
        AllureListener.setDriver(driver);
        driver.get(AppConstants.BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("{stepName}")
    protected void logStep(String stepName) {
        System.out.println("▶ " + stepName);
    }

    protected void attachScreenshot() {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }
    }
}