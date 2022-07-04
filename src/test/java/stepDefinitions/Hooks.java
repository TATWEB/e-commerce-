package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

import java.sql.SQLOutput;
import java.time.Duration;

public class Hooks {

//     Hook methods run before and after each scenario
    @Before
    public void setupScenario(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
        Driver.getDriver().manage().window().maximize();
    }


//    @Before ("@db")
//    public void setupScenarioForDb(){
//        System.out.println("Database Connection established");
//
//    }

    @After
    public void tearDownScenario(Scenario scenario){

        if(scenario.isFailed()){

            byte[] screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs, "image/png", "failed_scenario_screenshot");
        }


        Driver.quitDriver();
    }

//    @After ("@db")
//    public void tearDownScenarioDB(){
//        System.out.println("Close the db connection");
//
//    }



}
