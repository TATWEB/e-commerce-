package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        tags = "@temp",
        features = "src/test/resources",  // the relative path of the folder where the feature files are located
        glue = "stepDefinitions"


)

public class ParallelRunner {
}
