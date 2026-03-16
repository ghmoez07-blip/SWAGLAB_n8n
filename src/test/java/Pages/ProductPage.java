package Pages;

import Utils.VisualValidation;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    By AddtocartBtn= By.id("add-to-cart-sauce-labs-backpack");
    By Panier=By.cssSelector("#shopping_cart_container > a");
    By ItemnamePanier=By.cssSelector("#item_4_title_link > div");
    By ItemnameProduct1=By.cssSelector("#item_4_title_link > div");
    @FindBy(css = "#item_4_title_link > div")
           List<WebElement>  ItemnameProduct;
    By product= By.cssSelector("#item_5_title_link > div");
    By badge = By.cssSelector("#shopping_cart_container > a > span");
    By deuxiemeproduit=By.cssSelector("#add-to-cart-test\\.allthethings\\(\\)-t-shirt-\\(red\\)");
    By RemoveBtn =By.id("remove-sauce-labs-backpack");
    //By Filtreicon =By.cssSelector("#header_container > div.header_secondary_container > div > span > select");
    @FindBy(css ="#header_container > div.header_secondary_container > div > span > select")
          WebElement   Filtreicon ;
    By ZTOA =By.cssSelector("#header_container > div.header_secondary_container > div > span > span");
    By productnames =By.className("inventory_item_name");
    By CheckoutBtn= By.id("checkout");
    By Firstname = By.id("first-name");
    By Lastname=By.id("last-name");
    By codepostal=By.id("postal-code");
    By ContinueBtn =By.id("continue");
    By Finish=By.id("finish");
    By MsgSuccesfulCheckout=By.cssSelector("#checkout_complete_container > h2");
    By msgcheckoutfailed =By.cssSelector("#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error");

    By itemTotalPrice = By.className("summary_subtotal_label");
    By taxPrice = By.className("summary_tax_label");
    By totalPrice = By.className("summary_total_label");
    By inventoryItemPrice = By.className("inventory_item_price");


    public ProductPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void AddTocart(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(AddtocartBtn));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(AddtocartBtn));}

    public  void ClickPanier(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(Panier));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(Panier));
    }
    public void verifPanier (){
        String ActualLink= driver.getCurrentUrl();
        Assert.assertEquals("panier non ouvert", "https://www.saucedemo.com/cart.html",ActualLink);
    }
    public void VerifAjoutProduitPanier(){
        String Actualproduct=driver.findElement(ItemnamePanier).getText();
        String Expectedproduct=driver.findElement(ItemnameProduct1).getText();

        Assert.assertEquals("Ajout echoué",Expectedproduct,Actualproduct);
    }

    public void clickProduit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(product)).click();
    }

    public String getBadgeText(){
        if(driver.findElements(badge).size() == 0){
            return "0";
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(badge)).getText();
    }
    public int getnumberBadge(){
        if(driver.findElements(badge).size() == 0){
        return 0;
    }
        return Integer.parseInt(getBadgeText());
    }
    public void VerifBadgeisEmpty(){
        Assert.assertEquals("panier pas vide",0,getnumberBadge());
    }
    public void VerifBadge(){

        Assert.assertEquals("notif failed","1",getBadgeText());
    }
    public void AddtocartDeuxiemeproduit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(deuxiemeproduit));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(deuxiemeproduit));
    }
    public  void ClickRemoveBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(RemoveBtn)).click();
    }
    public void VerifProduitSupprime(){
        /*if(driver.findElements(ItemnameProduct).size() == 0){
            return "ok, produit supprimé";
        }
        return "produit trouvé";

         */

    Assert.assertTrue(ItemnameProduct.isEmpty());
    }

    public void ClickFilter(){
       // wait.until(ExpectedConditions.visibilityOfElementLocated(Filtreicon)).click();
        Select S =new Select(Filtreicon);
        S.selectByVisibleText("Name (Z to A)");
    }
    //public void clickOptionZTOA(){
       // wait.until(ExpectedConditions.visibilityOfElementLocated(ZTOA)).click();}

    public void VerifFiltrerFromZToA(){
        List<WebElement> elements=driver.findElements(productnames);
        List<String> ActualNames=new ArrayList<>();
        for (WebElement e1:elements){
            ActualNames.add(e1.getText());
        }
        List<String> ExpectedList=new ArrayList<>(ActualNames);
        ExpectedList.sort(Collections.reverseOrder());
        Assert.assertEquals("Sort from Z to A failed", ExpectedList,ActualNames);

    }
    public  void ClickCheckout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutBtn));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(CheckoutBtn));
    }
    public void verifcheckoutPage(){
        String ActualLink= driver.getCurrentUrl();
        Assert.assertEquals("","https://www.saucedemo.com/checkout-step-one.html",ActualLink);
    }
    public void nameinput (String name ){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname)).sendKeys(name);
       /* WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(Firstname));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", element, name);*/
    }
    public void lastnameinput (String last ){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Lastname)).sendKeys(last);
       /* WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(Lastname));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", element, last);*/
    }
    public void codeinput (String code ){
        wait.until(ExpectedConditions.visibilityOfElementLocated(codepostal)).sendKeys(code);
       /* WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(codepostal));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", element, code);*/
    }
    public  void ClickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(ContinueBtn)).click();
       // JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("arguments[0].click();", driver.findElement(ContinueBtn));
    }
    public void VerifClickcontinue(){
        String Actuallink = driver.getCurrentUrl();
        Assert.assertEquals("continue BTN failed ","https://www.saucedemo.com/checkout-step-two.html",Actuallink);
    }
    public  void ClickFinish(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Finish)).click();
    }
    public void SuccessfulCheckout(){
        String ActualLink= driver.getCurrentUrl();
        Assert.assertEquals("Finish BTN failed ","https://www.saucedemo.com/checkout-complete.html",ActualLink);
        String Actualmsg = driver.findElement(MsgSuccesfulCheckout).getText();
        Assert.assertEquals("msg missed","Thank you for your order!",Actualmsg);

    }
    public String getmsgfailedcheckout(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(msgcheckoutfailed)).getText();

    }



    public void verifierCalculTotalDeuxProduits() {
        // 1. Récupérer tous les prix des articles affichés dans le résumé
        List<WebElement> pricesElements = driver.findElements(inventoryItemPrice);
        double sommeArticlesCalculee = 0;

        for (WebElement priceElement : pricesElements) {
            // On enlève le "$" et on convertit en double
            String priceText = priceElement.getText().replace("$", "");
            sommeArticlesCalculee += Double.parseDouble(priceText);
        }

        // 2. Récupérer le "Item total" affiché par le site (Subtotal)
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalPrice)).getText();
        double subtotalAffiche = Double.parseDouble(subtotalText.replace("Item total: $", ""));

        // 3. Récupérer la taxe
        String taxText = driver.findElement(taxPrice).getText();
        double taxAffichee = Double.parseDouble(taxText.replace("Tax: $", ""));

        // 4. Récupérer le Total final
        String totalText = driver.findElement(totalPrice).getText();
        double totalFinalAffiche = Double.parseDouble(totalText.replace("Total: $", ""));

        // --- ASSERTIONS ---

        // Vérifier que la somme des articles correspond au sous-total
        Assert.assertEquals("La somme des articles ne correspond pas au sous-total",
                sommeArticlesCalculee, subtotalAffiche, 0.01);

        // Vérifier que Sous-Total + Taxe = Total Final
        double calculTotalTheorique = subtotalAffiche + taxAffichee;
        Assert.assertEquals("Le montant total final (Prix + Taxe) est incorrect",
                calculTotalTheorique, totalFinalAffiche, 0.01);
    }


    public void verifierIconeParImage() throws IOException {
        // 1. Prendre une capture d'écran
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "target/screenshot.png";
        FileUtils.copyFile(screenshot, new File(path));

        // 2. Comparer avec l'image de référence (ex: src/test/resources/cart_icon.png)
        boolean isVisible = VisualValidation.isElementPresent(path, "src/test/resources/cart_icon.png", 0.9);
        Assert.assertTrue("L'icône du panier n'est pas graphiquement correcte", isVisible);
    }


}
