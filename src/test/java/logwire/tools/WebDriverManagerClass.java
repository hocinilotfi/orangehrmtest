package logwire.tools;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManagerClass {
    private static WebDriver driver;
    private static final String SELENIUM_GRID_URL = "http://selenium-hub:4444/wd/hub"; // URL de votre Selenium Grid

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");

            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (browser.toLowerCase()) {
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    break;
                case "chrome":
                default:
                    capabilities.setBrowserName("chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    break;
            }

            try {
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("URL malformed for Selenium Grid: " + SELENIUM_GRID_URL);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
