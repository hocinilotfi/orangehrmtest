package logwire.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "username")
    private WebElement identifiantElement;
    
    @FindBy(name = "password")
    private WebElement passwordElement;

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/header/div[1]/div[1]/span/h6")
    private WebElement pageSuccess;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    private WebElement btnSubmit;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
    private WebElement errorElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterIdentifiant(String identifiant) {
        wait.until(ExpectedConditions.visibilityOf(identifiantElement));
        identifiantElement.clear();
        identifiantElement.sendKeys(identifiant);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
        btnSubmit.click();
    }

    public boolean isSuccessLogin() {
        wait.until(ExpectedConditions.visibilityOf(pageSuccess));
        return pageSuccess.isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorElement));
        return errorElement.isDisplayed();
    }
}
