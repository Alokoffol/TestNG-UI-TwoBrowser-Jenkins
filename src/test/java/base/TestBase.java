package base;

import constants.AppConstants;
import driver.DriverFactory;
import io.qameta.allure.Step;
import listeners.AllureListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

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

}