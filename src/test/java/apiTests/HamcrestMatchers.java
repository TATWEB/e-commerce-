package apiTests;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchers {


    @Test

    public void hamcrestMatchersExample(){
        baseURI = "http://localhost:8080/app";

        JsonPath jsonPath = given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").
                body("[0].name", equalTo("Resident Evil 4")).
                body("$.size()", equalTo(10)).
                body("name", hasItem("Tetris")).
                body("name", hasItems("Tetris", "Gran Turismo 3", "Resident Evil 4")).
//                body("name", contains("Tetris", "Super Mario 64")).
                 body("name", not(empty())).
                extract().jsonPath();

        // If the array is anonymous (if it is the root element), to access it sub element keys
        // you can simply mention the keys -> "name" (not "$.name")
        System.out.println(jsonPath.getList("name"));

    }

    @Test
    public void hamcrestMatchersExample2(){
        baseURI = "http://localhost:8080/app";

       given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                body("[1]", hasKey("category")).
                body("[1]", hasValue("Driving")).
                body("[1]", hasEntry("name", "Gran Turismo 3")).
                statusCode(200);
        }
}
