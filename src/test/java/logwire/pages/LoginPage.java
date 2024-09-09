package logwire.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;
    @FindBy(name = "username")
    private WebElement identifiantEelment;
    
    @FindBy(name="password")
    private WebElement passwordElement;

    @FindBy(xpath="/html/body/div/div[1]/div[1]/header/div[1]/div[1]/span/h6")
    private WebElement pageSuccess;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    // .submit-row > input:nth-child(1)
    ///html/body/div/div/main/div[1]/div/form/div[3]/input
    private WebElement btnSubmit;

    @FindBy(xpath = "/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
    private WebElement errorElement;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterIdentifiant(String identifiant) {
        identifiantEelment.clear();
        identifiantEelment.sendKeys(identifiant);
    }
    public void enterPassword(String password) {
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickButton(){
        btnSubmit.click();
    }

     public boolean isSuccessLogin(){
        // Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        return pageSuccess.isDisplayed();
    }
    public boolean isErrorMessageDisplayed(){
        return errorElement.isDisplayed();
    }
  

}