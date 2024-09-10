package logwire.tools;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerClass{
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
                    options2.addArguments("--headless");
                    // options2.addArguments("--no-sandbox");
                    // options2.addArguments("--disable-dev-shm-usage");
                    // options2.addArguments("--disable-gpu");
                    //driver = new ChromeDriver(options2);
                    WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker();
                    driver = wdm.create();
                    // driver = WebDriverManager.chromedriver().create();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
