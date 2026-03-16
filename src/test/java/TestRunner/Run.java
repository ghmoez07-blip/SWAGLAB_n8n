package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Chemin vers vos fichiers .feature
        features = "src/test/java/Features/Product.feature",
        // Chemin vers vos Step Definitions et Hooks
        glue = "Steps",
        tags = "@Product",

        // Plugins pour la génération des rapports
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html", // Rapport standard
                "json:target/cucumber.json",           // Requis pour les rapports pros
                "junit:target/cucumber.xml"          // Rapport pour Jenkins/CI
        }

)

public class Run {
}
