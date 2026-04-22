package driver;

import constants.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.AppConstants.CHROME_DRIVER_PATH;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        ChromeOptions options = ChromeOptionsConfig.createChromeOptions(headless);

        if ("yandex".equalsIgnoreCase(browserName)) {

            options.setBinary(AppConstants.YANDEX_BROWSER_BINARY_PATH);

            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        } else if ("chrome".equalsIgnoreCase(browserName)) {

            WebDriverManager.chromedriver().setup();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }

        WebDriver driver = new ChromeDriver(options);
        driver.get(AppConstants.BASE_URL);
        return driver;
    }
}