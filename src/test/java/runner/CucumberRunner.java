package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        tags = "@ui", //@second and @temp , @second or @temp, not @temp, @test or @temp and @second,  (@test or @temp) and @second
        features = "src/test/resources",  // the relative path of the folder where the feature files are located
        glue = "stepDefinitions", // the relative path of the folder where step definition methods are located
        stepNotifications = true  // shows the detailed step results
//        ,dryRun = true   // dryRun scans the resources folder and generates step definition snippets
        // used for generating snippets quickly
        ,snippets = CucumberOptions.SnippetType.CAMELCASE  // generates Camel Case Style Snippets
        ,plugin = {
                 "pretty",  // displays more detailed output on the console
                 "html:target/cucumber-built-in-report.html", // generates built in html report at the given the path
                 "json:target/cucumber.json", // generates json report
                  "rerun:target/failed.txt" // generates a text file with the list of failed scenarios
        }
)

public class CucumberRunner {
}
