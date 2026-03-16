package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage2 {

    WebDriver driver;
    WebDriverWait wait;

    By product= By.cssSelector("#item_5_title_link > div");
    By BtnAddtocart=By.id("add-to-cart");
    By Panier=By.cssSelector("#shopping_cart_container > a");
    By ProductinPanier=By.cssSelector("#item_5_title_link > div");

    public ProductPage2(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(30)); }

    public void ClickAddtocart(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(BtnAddtocart)).click();
    }
    public void ClickPanier(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Panier)).click();}

    public void verifPanier (){
        String ActualLink= driver.getCurrentUrl();
        Assert.assertEquals("panier non ouvert", "https://www.saucedemo.com/cart.html",ActualLink);
    }
    public void VerifAjoutProduitPanier(){
        String Actualproduct=driver.findElement(ProductinPanier).getText();
        String Expectedproduct=driver.findElement(product).getText();

        Assert.assertEquals("Ajout echoué",Expectedproduct,Actualproduct);
    }
}
