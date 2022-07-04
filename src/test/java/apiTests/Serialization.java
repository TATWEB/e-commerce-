package apiTests;

import apiTests.pojos.VideoGame;
import io.restassured.mapper.ObjectMapperType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialization {


     //Serialization - process of converting Java type into stream of bytes to be sent across the network
    // Deserialization - opposite process of converting bytes into Java type.

    @BeforeClass
    public static void setupBaseUri(){
        baseURI = "http://localhost:8080/app"; // sets the base URI
    }

    @Test
    public void serializationUsingMaps() {




        int id = 100 + (int) (Math.random() * 900);

        Map<String, Object> videogame = new LinkedHashMap<>();
        videogame.put("id", id);
        videogame.put("name", "Super Mario");
        videogame.put("releaseDate", "2022-01-03T23:39:32.200Z");
        videogame.put("reviewScore", 99);
        videogame.put("category", "Adventure");
        videogame.put("rating", "General");



        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                //Rest assured has built in Serializers (Obect mappers) and you can specify it along with your body
                // body(videogame, ObjectMapperType.JACKSON_2).
                body(videogame).  // Java object (Map) is going to serialized and sent across the network
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
    public void serializationUsingPOJOS() {


       // POJO -> Plain Old Java Object

        int id = 100 + (int) (Math.random() * 900);


//        VideoGame videoGame = new VideoGame();
//        videoGame.setId(id);
//        videoGame.setName("Mario");
//        videoGame.setCategory("FPS");
//        videoGame.setRating("PG13");
//        videoGame.setReviewScore(99);
//        videoGame.setReleaseDate("1985-03-03");

        VideoGame videoGame = new VideoGame(id, "Mario", "1985-09-09", 99, "FPS", "99"  );


        given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                //Rest assured has built in Serializers (Obect mappers) and you can specify it along with your body

                        body(videoGame).  // Java custom object (POJO) is going to serialized and sent across the network
                when().log().all().
                post("/videogames").
                then().log().all().
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").
                header("Content-Length", "39").
                body("status", equalTo("Record Added Successfully"));


    }


}
