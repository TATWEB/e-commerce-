package stepDefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VideoGameApiStepDefs {


    RequestSpecification requestspecs;
    Response response;

    @Given("the baseURI is initialized and the header {string} , {string} is set")
    public void theHeaderIsSet(String headerKey, String headerValue) {

        baseURI = "http://localhost:8080/app";

        requestspecs = given(). // anything that you send along with the request is added here
                header(headerKey, headerValue);


    }
    @When("I send a GET request to {string} endpoint")
    public void iSendAGETRequestToEndpoint(String endpoint) {
        response = requestspecs.when().log().all().  // indicate what type of request and the endpoint
                get(endpoint);


    }
    @Then("The status code should be {int}, the header should be {string}, {string} and the returned response should have {int} items")
    public void theStatusCodeShouldBeTheHeaderShouldBeAndTheReturnedResponseShouldHaveItems(Integer statusCode, String headerKey, String headerValue, Integer size) {
        response.then(). log().all().
                assertThat().
                statusCode(statusCode).
                header(headerKey, headerValue).
                body("$.size()", equalTo(size));
    }
}
