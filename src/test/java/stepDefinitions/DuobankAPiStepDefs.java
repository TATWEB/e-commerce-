package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.APIUtils;
import utilities.Endpoints;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DuobankAPiStepDefs {


    RequestSpecification requestspecs;
    Response response;

    String token;

    @Given("I am logged in as a valid user with username {string}  and password {string} and I have the JWT token")
    public void iAmLoggedInAsAValidUserWithUsernameAndPasswordAndIHaveTheJWTToken(String user, String pass) {

        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

       token = APIUtils.generateToken(user, pass);

        System.out.println(token);
    }



    @When("I send a GET request to a {string} endpoint")
    public void iSendAGETRequestToAEndpoint(String string) {

        response = given().
                header("Authorization", token).
                when().log().all().
                get(Endpoints.GET_ALL_MORTGAGES);
    }
    @Then("I should be able to retrieve all the mortgage applications that belong to me")
    public void iShouldBeAbleToRetrieveAllTheMortgageApplicationsThatBelongToMe() {
        response.then().log().all().
                statusCode(200).
                body("mortagage_applications.size()", is(297));
    }

}
