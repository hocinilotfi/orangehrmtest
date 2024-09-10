package logwire.stepDefs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

import logwire.pages.LoginPage;
import logwire.tools.WebDriverManagerClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginPageSteps() {
        this.driver = WebDriverManagerClass.getDriver();
        this.loginPage = new LoginPage(this.driver);
    }

    @When("je clique sur le bouton Login")
    public void je_clique_sur_le_bouton_login() {
        // Write code here that turns the phrase above into concrete actions
        // assertTrue(true);
        this.loginPage.clickButton();
    }

    @Then("j accede a mon espace admin")
    public void j_accede_a_mon_espace_admin() {
        // Write code here that turns the phrase above into concrete actions
        // assertTrue(true);
        assertTrue(this.loginPage.isSuccessLogin());

    }

    @When("je saisis le nom d uilisateur {string}")
    public void je_saisis_le_nom_d_uilisateur(String s) {
        // Write code here that turns the phrase above into concrete actions
        // assertTrue(true);
        this.loginPage.enterIdentifiant(s);
    }

    @Given("je me rends sur le lien {string}")
    public void je_me_rends_sur_le_lien(String s) {
        // Write code here that turns the phrase above into concrete actions
        //assertTrue(true);
        this.driver.get(s);
    }

    @When("je saisis le mot de passe {string}")
    public void je_saisis_le_mot_de_passe(String s) {
        // Write code here that turns the phrase above into concrete actions
        // assertTrue(true);
        this.loginPage.enterPassword(s);
    }

    @Then("une erreur s affiche")
    public void une_erreur_s_affiche() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(this.loginPage.isErrorMessageDisplayed());
    }

}