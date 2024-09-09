package logwire.hooks;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import logwire.tools.WebDriverManager;

import java.io.File;
import java.nio.file.Files;

public class Hooks {
    @Before
    public void setUp() {

    }

    //@After
    //public void quit(){
    //    WebDriverManager.quitDriver();
    //}

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
        WebDriverManager.quitDriver();
    }

    private void takeScreenshot(Scenario scenario) {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            
            try {
                Path screenshotPath = Paths.get("target/screenshots", scenario.getName() + ".png");
                Files.createDirectories(screenshotPath.getParent());
                Files.write(screenshotPath, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
