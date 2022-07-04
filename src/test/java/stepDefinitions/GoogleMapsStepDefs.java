package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GoogleMapsStepDefs {


    RequestSpecification requestspecs;
    Response response;

    @Given("The Base URI is initialized and the following headers are added")
    public void theBaseURIIsInitializedAndTheFollowingHeadersAreAdded(Map<String, String> headers) {
       baseURI = "https://maps.googleapis.com/maps/api/place";

        Set<String> keys = headers.keySet();
        Iterator<String> iterator = keys.iterator();
        String str;
        requestspecs = given().log().all().
                queryParam(str = iterator.next(), headers.get(str)).
                queryParam(str = iterator.next(), headers.get(str)).
                queryParam(str = iterator.next(), headers.get(str)).
                queryParam(str = iterator.next(), headers.get(str));


    }
    @When("I send a GET request to the {string} endpoint")
    public void iSendAGETRequestToTheEndpoint(String endpoint) {
         response = requestspecs.when().log().all().
                get(endpoint);
    }
    @Then("The following should be correct")
    public void theFollowingShouldBeCorrect(Map<String, String> responseValues) {
        List<String> keys = new ArrayList<>(responseValues.keySet());


        System.out.println(keys);


        System.out.println("candiates[0]." + keys.get(1));
        System.out.println(responseValues.get(keys.get(1)));
        System.out.println("candiates[0]." + keys.get(2));



        response.then().log().all().
                assertThat().
                statusCode(Integer.parseInt((responseValues.get(keys.get(0))))).
                body("candidates[0]." + keys.get(1), is(responseValues.get(keys.get(1)))).
                body("candidates[0]." + keys.get(2), containsString(responseValues.get(keys.get(2))));

    }


}
