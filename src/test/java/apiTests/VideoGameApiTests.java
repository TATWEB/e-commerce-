package apiTests;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class VideoGameApiTests {


//    static {
//        baseURI = "http://localhost:8080/app"; // sets the base url
//    }
// as the baseURI will be used in multiple methods we can use it once in static block


    // or in Before Class
    @BeforeClass
    public static void setupBaseURI() {
        baseURI = "http://localhost:8080/app";
    }

    @Test
    public void testGetVideogames() {
        //Rest Assured methods use Builder pattern
        //Rest Assured also uses Gherkin format


        given(). // anything that you send along with your request is added here
                header("Accept", "application/json").
                when().log().all(). // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").
                header("Content-Length", "1183");

    }

    @Test
    public void testPOSTVideogames() {
        int id = 100 + (int) (Math.random() * 900);
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"name\": \"Super Mario\",\n" +
                        "  \"releaseDate\": \"1990-05-26T08:05:01.016Z\",\n" +
                        "  \"reviewScore\": 90,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"GA\"\n" +
                        "}").
                when().log().all().
                post("videogames").
                then().log().all().
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").
                header("Content-Length", "39").
                body("status", equalTo("Record Added Successfully"));

 // send a get request for that specific game to verify successfully  game creation
        given().
                header("Accept", "application/json").
                pathParam("videoGameID", id).
        when().log().all().
                get("/videogames/{videoGameID}").
       then().log().all().
                statusCode(200).
                body("id", is(id)).
                body("name", equalTo("Super Mario"));


    }

    @Test
    public void testGetSpecificVideogame() {
        //Rest Assured methods use Builder pattern
        //Rest Assured also uses Gherkin format


        given(). // anything that you send along with your request is added here
                header("Accept", "application/json").
                pathParam("videoGameID", "1").
                when().log().all(). // indicate what type of request and the endpoint
                get("/videogames/{videoGameID").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
       //         body()
                header("Content-Type", "application/json").
                header("Content-Length", "1183");

    }

}
