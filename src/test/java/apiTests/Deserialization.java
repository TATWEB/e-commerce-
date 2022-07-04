package apiTests;

import apiTests.pojos.PlacePojosGeneratedFromOnlineSource.Place;
import apiTests.pojos.VideoGame;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Deserialization {


     //Serialization - process of converting Java type into stream of bytes to be sent across the network
    // Deserialization - opposite process of converting bytes into Java type.

    @BeforeClass
    public static void setupBaseUri(){
        baseURI = "http://localhost:8080/app"; // sets the base URI
    }

    @Test
    public void deserializationIntoMap(){

        Integer expectedId = 1;


        Map responseAsRawMap = given().
                header("Accept", "application/json").
                pathParam("videoGameId", expectedId).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(Map.class);

        System.out.println(responseAsRawMap);
        Integer actualId = (Integer)(responseAsRawMap.get("id"));

        Assert.assertEquals(expectedId,actualId );





    }


    @Test
    public void deserializationIntoMapOfSpecificType(){

        Integer expectedId = 1;

        Set<String> expectedKeys = new LinkedHashSet<>(Arrays.asList("id", "name", "releaseDate",
                "reviewScore", "category","rating"));

     Map<String, Object> responseAsSpecificMap =  given().
                header("Accept", "application/json").
                pathParam("videoGameId", expectedId).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(new TypeRef<Map<String,Object>>() {
        });


       Set<String> actualKeySet = responseAsSpecificMap.keySet();

       Assert.assertEquals(expectedKeys, actualKeySet);







    }


    @Test
    public void deserializationIntoList(){


        List responseAsList = given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(List.class);


        System.out.println(responseAsList);

        Assert.assertEquals(17, responseAsList.size());


    }

    @Test
    public void deserializationIntoListOfSpecificType(){


        List<Map<String,Object>> responseAsListOfMaps =   given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(new TypeRef<List<Map<String,Object>>>() {
       });


        System.out.println(responseAsListOfMaps);

        for (Map<String, Object> eachJson : responseAsListOfMaps) {

            Object id = eachJson.get("id");
            Assert.assertEquals(Integer.class, id.getClass());
        }


    }



    @Test
    public void deserializationIntoPOJO(){


        Integer expectedId = 1;


        VideoGame responseAsVideoGameObject = given().
                header("Accept", "application/json").
                pathParam("videoGameId", expectedId).
                when().log().all().
                get("/videogames/{videoGameId}").
                then().log().all().
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(VideoGame.class);


         System.out.println(responseAsVideoGameObject);
         Assert.assertEquals(expectedId, responseAsVideoGameObject.getId());
         Assert.assertEquals("Resident Evil", responseAsVideoGameObject.getName());







    }


    @Test
    public void deserializationIntoListOfPOJOS(){


        List<VideoGame> resultAsListOfVideogames = given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").extract().as(new TypeRef<List<VideoGame>>() {
        });


        for (VideoGame eachVideogame : resultAsListOfVideogames) {

            Assert.assertTrue(eachVideogame.getReviewScore()>=0 && eachVideogame.getReviewScore()<=100);
        }


    }


    @Test
    public void deserializeComplexJsonIntoMap(){
        baseURI = "https://maps.googleapis.com/maps/api/place";


        Map response =
                given().
                queryParam("fields", "geometry").
                queryParam("input", "Mike's American Grill").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("OK")).extract().as(Map.class);


        List candidates = (List)(response.get("candidates"));

        Map firstJson = (Map)(candidates.get(0));

        Map geometry = (Map)(firstJson.get("geometry"));

        Map location = (Map)(geometry.get("location"));

        System.out.println(location.get("lat"));
        System.out.println(location.get("lng"));


        Assert.assertEquals(38.7828306, location.get("lat"));


    }


    @Test
    public void deserializeComplexJsonIntoPOJO() {
        baseURI = "https://maps.googleapis.com/maps/api/place";


        Place responseAsPlacePOJO = given().
                queryParam("fields", "formatted_address,name,rating,opening_hours").
                queryParam("input", "Mike's American Grill").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("OK")).extract().as(Place.class);

        System.out.println(responseAsPlacePOJO);

        Assert.assertEquals("Mike's American", responseAsPlacePOJO.getCandidates().get(0).getName());
        Assert.assertEquals("OK", responseAsPlacePOJO.getStatus());
        Assert.assertEquals(true, responseAsPlacePOJO.getCandidates().get(0).getOpeningHours().getOpenNow());


    }







    }
