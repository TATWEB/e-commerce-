package apiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class VideoGameApiTests {

//    static {
//        baseURI = "http://localhost:8080/app"; // sets the base URI
//    }

   @BeforeClass
   public static void setupBaseUri(){
       baseURI = "http://localhost:8080/app"; // sets the base URI
   }


    @Test
    public void testGETVideogames(){

        // Rest Assured methods use Builder pattern

        // Rest Assured also uses Gherkin format

        //


        given(). // anything that you send along with the request is added here
               header("Accept", "application/json").
        when(). log().all().  // indicate what type of request and the endpoint
               get("/videogames").
        then(). log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json");



        RequestSpecification requestSpecification = given().
                         header("Accept", "application/json");


//
        Response response = requestSpecification.
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames");


        response.then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().jsonPath();


    }

    @Test
    public void testPOSTVideogame(){

        int id = 100+ (int) (Math.random()*900);
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
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
                statusCode(200).
                header("Content-Type", "application/json").
                header("Content-Length", "39").
                body("status", equalTo("Record Added Successfully"));

    }


    @Test
    public void testPOSTVideogameWithGETRequest(){

        int id = 100+ (int) (Math.random()*900);
        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body("{\n" +
                        "  \"id\": "+id+",\n" +
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
                statusCode(200).
                header("Content-Type", "application/json").
                header("Content-Length", "39").
                body("status", equalTo("Record Added Successfully"));

        // Send a get request for that specific game to verify the successful game creation
              given().
                      header("Accept", "application/json").
                      pathParam("videoGameId", id).

              when(). log().all().
                      get("/videogames/{videoGameId}").
              then(). log().all().
                     statusCode(200).
                      body("id", is(id)).
                      body("name", equalTo("Super Mario 64"));

    }


    @Test
    public void testGETSpecificVideogame(){

        // Rest Assured methods use Builder pattern

        // Rest Assured also uses Gherkin format

        given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                pathParam("videoGameId", 1).
                when(). log().all().  // indicate what type of request and the endpoint
                get("/videogames/{videoGameId}").
                then(). log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
//                body().
                header("Content-Type", "application/json");







    }


}
