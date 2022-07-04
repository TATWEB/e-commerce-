package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = "@target/failed.txt",
        glue = "stepDefinitions"


        ,plugin = {
                "pretty",
                 "html:target/cucumber-built-in-report-failed.html",
                 "json:target/cucumber.json"
        }
)

public class FailedScenariosRunner {
}
