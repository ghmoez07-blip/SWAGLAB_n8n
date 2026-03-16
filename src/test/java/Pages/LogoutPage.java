package Pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {

    WebDriver driver;
    WebDriverWait wait;

    By menuBtn = By.id("react-burger-menu-btn");
    By logoutBtn = By.cssSelector("#logout_sidebar_link");

    public LogoutPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    public void clickMenuBtn (){
        driver.findElement(menuBtn).click();
    }
    public void clickLogoutBtn (){

        WebElement logoutLink = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtn));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", logoutLink);
        js.executeScript("arguments[0].click();", logoutLink);
    }

       /* WebElement logoutLink = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtn));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }*/

       //WebElement logoutlink= wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));
       //logoutlink.click();
        //driver.findElement(logoutBtn).click();
    }




