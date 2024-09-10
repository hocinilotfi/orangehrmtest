package logwire.tools;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {
    private static WebDriver driver;
        public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            
            switch (browser.toLowerCase()) {
                case "firefox":
                    // System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless=new");
                    driver = new FirefoxDriver(options);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    // driver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                    ChromeOptions options2 = new ChromeOptions();
                    options2.addArguments("--headless=new");
                    driver = new ChromeDriver(options2);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
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
