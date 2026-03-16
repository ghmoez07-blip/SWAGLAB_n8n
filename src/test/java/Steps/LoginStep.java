package Steps;

import Pages.LoginPage;
import Pages.LogoutPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStep {
    WebDriver driver=Hook.driver ;
    LoginPage loginPage;
    LogoutPage logoutPage;

    @Given("je suis sur la page Login")
    public void je_suis_sur_la_page_login() {
        //driver=new ChromeDriver();
        //driver.manage().window().maximize();
        this.driver=Hook.driver;
        loginPage=new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
        logoutPage=new LogoutPage(driver);}

    @When("je saisi l'username {string}")
    public void je_saisi_l_username(String user) {
        loginPage.enterusername(user);
    }

    @When("je saisi le mot de passe {string}")
    public void je_saisi_le_mot_de_passe(String pass) {
        loginPage.enterpassword(pass);
    }

    @When("je click sur le bouton Login")
    public void je_click_sur_le_bouton_login() {loginPage.Login();}

    @Then("redirection vers la page Home")
    public void redirection_vers_la_page_home() {
        String Actuallink= driver.getCurrentUrl();
        Assert.assertEquals("login failed","https://www.saucedemo.com/inventory.html",Actuallink);
    }
    @Then("un msg derreur doit safficher {string}")
    public void unMsgDerreurDoitSafficher(String msgexpected) {
        String msgactuel = loginPage.getmessage();
        //Assert.assertTrue("msg invalide",msgactuel.contains(msgexpected));
        Assert.assertEquals(msgexpected,msgactuel);
        driver.quit();
    }
    @When("je click sur menuBtn")
    public void je_click_sur_menu_btn() {
        logoutPage.clickMenuBtn();
    }
    @When("je click sur logoutSidebarBtn")
    public void je_click_sur_logout_sidebar_btn() {
        logoutPage.clickLogoutBtn();
    }
    @Then("redirection vers la page login")
    public void redirection_vers_la_page_login() {
        String ActualLink = driver.getCurrentUrl();
        Assert.assertEquals("login page unreached","https://www.saucedemo.com/",ActualLink);
    }
}
