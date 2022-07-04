package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import pages.HomePage;
import pages.SearchPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class SearchStepDefs {



    String expectedSearchTerm;


    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I search for a {string}")
    public void i_search_for_a(String searchTermFromFeatureFile) {
        HomePage homePage = new HomePage();
        expectedSearchTerm = searchTermFromFeatureFile.toLowerCase();
        homePage.searchBox.sendKeys(expectedSearchTerm + Keys.ENTER);
    }


    @Then("I search for a Blouse") // "I search for a Blouse" -> Cucumber expression
    // Cucumber scenario step is matched to step definition method using the text of the scenario step
    // either with Regular Expressions or Cucumber Expressions
    public void iSearchForABlouse() {
        HomePage homePage = new HomePage();
        expectedSearchTerm = "Blouse".toLowerCase();
        homePage.searchBox.sendKeys(expectedSearchTerm + Keys.ENTER);
    }






    @Then("I should land on the search page")
    public void i_should_land_on_the_search_page_and_the_search_term_should_be_correct() {
        assertEquals("Search - My Store", Driver.getDriver().getTitle());

    }


    @Then("the search term should be correct")
    public void the_search_term_should_be_correct() {
        String actualSearchTerm =  new SearchPage().searchTerm.getText().toLowerCase() ;

        assertTrue(actualSearchTerm.contains(expectedSearchTerm+ "BLABALA"));


    }

    @When("I do not enter any search term")
    public void i_do_not_enter_any_search_term() {
       new HomePage().searchBox.sendKeys("" + Keys.ENTER);
    }
    @Then("the error message should be there")
    public void the_error_message_should_be_there() {
        Assert.assertTrue(new SearchPage().alert.isDisplayed() );
    }

    @When("I send the following query")
    public void i_send_the_following_query(String docString) {
        System.out.println("This is the docstring that is passed:");
        System.out.println(docString);
    }


    @When("I pass this information")
    public void iPassThisInformation(List<String> dataTable) {


        System.out.println(dataTable);

    }

    @When("I pass this information as List of Lists")
    public void iPassThisInformationAsListOfLists(List<List<String>> dataTable) {
        System.out.println(dataTable.get(0).get(1));
    }

    @When("I pass this information as List of Maps")
    public void iPassThisInformationAsListOfMaps(List<Map<String,String>> dataTable) {


        System.out.println(dataTable.get(0).get("phone"));
    }







//


}
