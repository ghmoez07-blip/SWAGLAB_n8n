package Pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    WebDriver driver ;
    WebDriverWait wait ;

    By Usernameinput= By.id("user-name");
    By passwordinput= By.id("password");
    By BtnLogin = By.id("login-button");
    By msgErreur= By.cssSelector("#login_button_container > div > form > div.error-message-container.error");

    public LoginPage (WebDriver driver ){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void enterusername (String username){
        driver.findElement(Usernameinput).sendKeys(username);
    }

    public void enterpassword (String password){
        driver.findElement(passwordinput).sendKeys(password);
    }
    public void Login (){
        driver.findElement(BtnLogin).click();
    }

    public String getmessage (){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(msgErreur)).getText();
    }

}
