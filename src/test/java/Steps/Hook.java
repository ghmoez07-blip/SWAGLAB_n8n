package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hook {

    // La variable doit être statique pour être partagée avec LoginSteps
    public static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Vérification cruciale : on ne prend la capture QUE si le driver existe
        if (driver != null) {
            if (scenario.isFailed()) {
                try {
                    final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Bug_SauceDemo");
                } catch (Exception e) {
                    System.out.println("Erreur lors de la capture : " + e.getMessage());
                }
            }
            // On ferme et on remet à null pour le prochain test
            driver.quit();
            driver = null;
        }
    }
}
