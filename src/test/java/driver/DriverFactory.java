package driver;

import constants.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.AppConstants.CHROME_DRIVER_PATH;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        // Используем твой готовый класс для настроек!
        ChromeOptions options = ChromeOptionsConfig.createChromeOptions(false);

        if ("yandex".equalsIgnoreCase(browserName)) {
            // Указываем путь к Яндекс Браузеру
            options.setBinary(AppConstants.YANDEX_BROWSER_BINARY_PATH);

            // Для Яндекс Браузера используем драйвер версии 144
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        } else if ("chrome".equalsIgnoreCase(browserName)) {
            // Для Chrome - автоматическая загрузка через WebDriverManager
            WebDriverManager.chromedriver().setup();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }

        WebDriver driver = new ChromeDriver(options);
        driver.get(AppConstants.BASE_URL);
        return driver;
    }
}