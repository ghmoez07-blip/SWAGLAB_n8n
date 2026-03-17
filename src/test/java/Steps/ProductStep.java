package Steps;

import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.ProductPage;
import Pages.ProductPage2;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductStep {

    //WebDriver driver =Hook.driver;
    WebDriver driver ;
    LoginPage loginpage ;
    ProductPage productPage;
    ProductPage2 productPage2;
    int CompteurAvant;

    @Given("je suis sur la page dacceuil")
    public void je_suis_sur_la_page_dacceuil() {
        //driver=new ChromeDriver();
       //driver.manage().window().maximize();
        this.driver=Hook.driver;
        loginpage=new LoginPage(driver);
        driver.get("https://www.saucedemo.com/");
        loginpage.enterusername("standard_user");
        loginpage.enterpassword("secret_sauce");
        loginpage.Login();
        productPage=new ProductPage(driver);
        productPage2=new ProductPage2(driver);
        productPage.getnumberBadge();
        productPage.VerifBadgeisEmpty();
        CompteurAvant = productPage.getnumberBadge();

    }
    @When("je click sur le bouton AddToCart")
    public void jeClickSurLeBoutonAddToCart() {productPage.AddTocart();}

    @And("je click sur le panier")
    public void jeClickSurLePanier() {
        productPage.ClickPanier();
        productPage.verifPanier();
    }

    @Then("produit ajouté au panier avec succés")
    public void produitAjoutéAuPanierAvecSuccés() {productPage.VerifAjoutProduitPanier();}

    @When("je click sur le produit")
    public void je_click_sur_le_produit() { productPage.clickProduit();}

    @When("je click sur le bouton AddToCart du produit")
    public void je_click_sur_le_bouton_add_to_cart_du_produit() {productPage2.ClickAddtocart();}

    @When("je click sur le panier du produit")
    public void je_click_sur_le_panier_du_produit() {
        productPage2.ClickPanier();
        productPage2.verifPanier();
    }

    @Then("produit ajouté au panier du produit avec succés")
    public void produit_ajouté_au_panier_du_produit_avec_succés()
    {productPage2.VerifAjoutProduitPanier();

    }
    @Then("notification dajout saffiche sur le panier")
    public void notificationDajoutSafficheSurLePanier() {
        productPage.getBadgeText();
        productPage.VerifBadge();
    }
    @Then("incremetation panier")
    public void incremetationPanier() {
        int CompteurApres=productPage.getnumberBadge();
        Assert.assertEquals("",CompteurAvant+1,CompteurApres);
    }
    @And("je click sur le bouton AddToCart DeuxiemeProduit")
    public void jeClickSurLeBoutonAddToCartDeuxiemeProduit() {
        productPage.AddtocartDeuxiemeproduit();
    }
    @And("je click sur Remove")
    public void jeClickSurRemove() {
        productPage.ClickRemoveBtn();
    }
    @Then("produit supprimé")
    public void produitSupprimé() {
        productPage.VerifProduitSupprime();
    }
    @Then("produits filtrés selon Name Z to A")
    public void produitsFiltrésSelonNameZToA() {
        productPage.VerifFiltrerFromZToA();
    }
    @When("je click sur filtreicon je choisi ZTOA")
    public void jeClickSurFiltreiconJeChoisiZTOA() {
        productPage.ClickFilter();

    }

    @When("je click checkout")
    public void jeClickCheckout() {productPage.ClickCheckout(); productPage.verifcheckoutPage();}

    @And("je saisi Firstname {string}")
    public void jeSaisiFirstname(String name) {productPage.nameinput(name);
    }
    @And("je saisi Lastname {string}")
    public void jeSaisiLastname(String last) {productPage.lastnameinput(last);}

    @And("je saisi Codepostal {string}")
    public void jeSaisiCodepostal(String code) {productPage.codeinput(code);

    }

    @And("je click Continue")
    public void jeClickContinue() {productPage.ClickContinue(); //productPage.VerifClickcontinue();
    }

    @And("je click Finish")
    public void jeClickFinish() {productPage.ClickFinish();}

    @Then("successful checkout")
    public void successfulCheckout() {productPage.SuccessfulCheckout();

    }

 @Then("cehckout failed affichage msg {string}")
 public void cehckoutFailedAffichageMsg(String Expectedmsg) {
     String ActualMsg=productPage.getmsgfailedcheckout();
     Assert.assertEquals("failed",Expectedmsg,ActualMsg);
 }

 @Then("le prix total des deux produits doit être correct")
 public void lePrixTotalDesDeuxProduitsDoitEtreCorrect() {
        //System.out.println(+productPage.verifierCalculTotalDeuxProduits2());
  productPage.verifierCalculTotalDeuxProduits();

 }
}
