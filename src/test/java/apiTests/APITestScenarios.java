package apiTests;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class APITestScenarios {


    @Test
    public void testPositiveScenarioWithValidRequiredParameters() {
        baseURI = "https://maps.googleapis.com/maps/api/place";


      given().
             // test the happy path with the required params (input,inputtype,key)
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("OK"));

    }

    @Test
    public void testPositiveScenarioWithVAlidRequiredParametersAndValidOptionalParameters() {
        baseURI = "https://maps.googleapis.com/maps/api/place";


        given().
                // test the happy path with the required params (input,inputtype,key) and optional parameters
                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").
                queryParam("language", "pl").
                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("OK"));

    }



    @Test
    public void negativeScenarioWithValidInputCreateGAmeThatALreadyExists(){

        baseURI = "http://localhost:8080/app";
        // Assuming the videogame with id 1 already exists, send a request and verify that the error response
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"Super Mario\",\n" +
                        "  \"releaseDate\": \"1985-12-30T00:08:43.146Z\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"GA\"\n" +
                        "}").
                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(not(200)).
                statusCode(500);


    }


    @Test
    public void negativeScenarioWithValidInputDeleteGAmeThatDoesNotExist() {
        baseURI = "http://localhost:8080/app";

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", 11).
                when().log().all().  // indicate what type of request and the endpoint
                delete("/videogames/{videoGameId}").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(not(200)); // bug

    }


    @Test
    public void negativeScenarioWithValidInputIllgalRange() {
        baseURI = "http://localhost:8080/app";

        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                pathParam("videoGameId", 1).
                body("{\n" +
                        "        \"id\": 6251353515365216,\n" +
                        "            \"name\": \"Mario\",\n" +
                        "            \"releaseDate\": \"2022-01-06T00:36:29.749Z\",\n" +
                        "            \"reviewScore\": 99,\n" +
                        "            \"category\": \"FPS\",\n" +
                        "            \"rating\": \"PG13\"\n" +
                        "    }").

                when().log().all().  // indicate what type of request and the endpoint
                put("/videogames/{videoGameId}").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(not(200));

    }


    @Test
    public void negativeScenarioWithInvalidInputMissingAPIKey() {
        baseURI = "https://maps.googleapis.com/maps/api/place";


        given().

                queryParam("input", "Duotech Academy").
                queryParam("inputtype", "textquery").

                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("REQUEST_DENIED"));

    }


    @Test ()
    public void negativeScenarioWithInvalidInputMissingRequiredParam() {

        baseURI = "http://localhost:8080/app";
        // Assuming the videogame with id 1 already exists, send a request and verify that the error response
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").

                body("{\n" +
                        "        \"id\": 1,\n" +
                        "            \"name\": \"Mario\",\n" +
                        "            \"releaseDate\": \"2022-01-06T00:36:29.749Z\",\n" +
                        "            \"reviewScore\": 99,\n" +
                        "            \"category\": \"FPS\",\n" +
                        "            \"rating\": \"PG13\"\n" +
                        "    }").

                when().log().all().  // indicate what type of request and the endpoint
                put("/videogames/{videoGameId}").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(not(200));

    }

    @Test
    public void negativeSceanrioInvalidMethodType(){
        baseURI = "https://maps.googleapis.com/maps/api/place";


        given().
                queryParam("fields", "geometry").
                queryParam("input", "Mike's American Grill").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                delete("/findplacefromtext/json").  // trying to call endpoint that requires get method with delete method
                then().log().all().
                statusCode(405); // expected status code when method is not allowed











    }



    }
