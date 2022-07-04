package apiTests;

import apiTests.pojos.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class EndToEndScenario {


    @Test
    public void endToEndTest(){
        baseURI = "http://localhost:8080/app";

        // Create a video game with POST
        int id = 100 + (int) (Math.random() * 900);


        VideoGame videoGame = new VideoGame(id, "Mario", "1985-09-09", 99, "FPS", "99"  );


        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").

                        body(videoGame).
                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(200);

        // Verify it with GET

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).

                when(). log().all().
                get("/videogames/{videoGameId}").
                then(). log().all().
                statusCode(200).
                body("id", is(id));



        // Update the same video game details with PUT

        VideoGame videoGameUpdated = new VideoGame(id, "Half Life", "1999-09-09", 99, "FPS", "PG13"  );

        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                 pathParam("videoGameId", id).
                body(videoGameUpdated).

                when().log().all().  // indicate what type of request and the endpoint
                put("/videogames/{videoGameId}").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200);

        // Verify it with GET
        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).

                when(). log().all().
                get("/videogames/{videoGameId}").
                then(). log().all().
                statusCode(200).
                body("id", is(id)).
                body("name", is("Half Life")).

                body("reviewScore", is(99)).
                body("rating", is("PG13")).extract().as(VideoGame.class);




        // Delete the same video game with DELETE

        given().

                header("Accept", "application/json").
                pathParam("videoGameId", id).


                when().log().all().  // indicate what type of request and the endpoint
                delete("/videogames/{videoGameId}").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                body("status", is("Record Deleted Successfully"));


        // Verify the video game no longer exists with GET

        given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).

                when(). log().all().
                get("/videogames/{videoGameId}").
                then(). log().all().
                statusCode(not(200));


    }


}
