package apiTests;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class JsonPathExample {


    @Test
    public void duoBankGetMortgage(){

        // Create a new user through API call
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

        String email = "magdalena@gmail.com";
        String pass = "magdalena2020";
        JsonPath jsonPath = given().
                body("{\n" +
                        "    \"email\":\"" + email + "\",\n" +
                        "    \"password\":\"" + pass + "\"\n" +
                        "}").
                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200).
                body("message", equalTo("You have successfully logged in.")).extract().jsonPath();

        String token = jsonPath.getString("token");// JsonPath uses Groovy Gpath expression


        System.out.println(token);


        given().
                header("Authorization", token).
                when().log().all().
                get("/getmortagage.php").
                then().log().all().
                statusCode(200).
                body("success", equalTo(1));





    }



    @Test
    public void groovyGpathExamples(){
        baseURI = "https://maps.googleapis.com/maps/api/place";


        JsonPath jsonPath = given().
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photo").
                queryParam("input", "Mike's American Grill").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("status", equalTo("OK")).
                body("candidates[0].formatted_address", equalTo("6210 Backlick Rd, Springfield, VA 22150, United States")).
                body("candidates[0].geometry.location.lat", equalTo(38.78283F)).
                body("candidates[0].photos[0].html_attributions[0]", equalTo("<a href=\"https://maps.google.com/maps/contrib/104540936204395176592\">Mike&#39;s &quot;American&quot;</a>")).

                time(lessThan(2000L)).
                extract().jsonPath();



        //  Extract "candidates"
        List<Object> candidates = jsonPath.getList("candidates");


        System.out.println(candidates);


        //  Extract "formatted_address"
        String formatted_address = jsonPath.getString("candidates[0].formatted_address");

        System.out.println(formatted_address);

        //  Extract "lat"
        Double lat = jsonPath.getDouble("candidates[0].geometry.location.lat");

        System.out.println(lat);

    }


    @Test
    public void groovyPathArrayExample(){

       baseURI = "http://localhost:8080/app";

        JsonPath response = given(). // anything that you send along with the request is added here
                header("Accept", "application/json").
                when().log().all().  // indicate what type of request and the endpoint
                get("/videogames").
                then().log().all(). // assertions on the returned response are put here
                assertThat().
                statusCode(200).
                header("Content-Type", "application/json").
                body("[0].name", equalTo("Resident Evil 4")).
                body("$.size()", equalTo(10)).extract().jsonPath();

                // $ -> represents the root array. Used to access array or its elements when the response is an array
                 Object array = response.get("$");

                System.out.println(array);

                Object names = response.get(".name");

               System.out.println(names);




    }


    @Test
    public void testGroovyPathWith(){


        baseURI = "https://api.football-data.org/v2";
        JsonPath jsonPath = given().
                header("X-Auth-Token", "50bcf727783a4b6ba8191b1f7aab8940").
                when().log().all().
                get("/teams").
                then().log().all().
                statusCode(200).
                body("teams.size()", equalTo(52)).extract().jsonPath();

        Assert.assertEquals(jsonPath.getInt("count"), jsonPath.getInt("teams.size()"));


        // Returns the names of teams as a List
        List listOfNames = jsonPath.get("teams.name");

        System.out.println(listOfNames);

        // Find the first occurrence of Everton FC
        String findSignle =  jsonPath.get("teams.name.find{it == 'Everton FC'}");

        System.out.println(findSignle);

        // Find all teams whose name starts with 'M'

        List allTeamsM =  jsonPath.get("teams.name.findAll{ it.startsWith('M') }");

        System.out.println(allTeamsM);

        // Find all teams that are founded after 1900

        List allTeamsafter1900 =  jsonPath.get("teams.findAll{ it.founded > 1900 }.name");

        System.out.println(allTeamsafter1900);

        List allTeamswithNUllPhoneNo=  jsonPath.get("teams.findAll{ it.email == null || it.phone == null }.name");

        System.out.println(allTeamswithNUllPhoneNo);









    }

    @Test
    public void testGroovyPathWithFootbalAPI() {


        baseURI = "https://api.football-data.org/v2";
        JsonPath jsonPath = given().
                header("X-Auth-Token", "50bcf727783a4b6ba8191b1f7aab8940").
                when().log().all().
                get("/teams/66").
                then().log().all().
                statusCode(200).extract().jsonPath();


        List allDateOfBirth = jsonPath.get("squad.collect{ it.dateOfBirth.substring(0,4) }");

        System.out.println(allDateOfBirth);


        // Find the oldest  (min dateOfBirth)

        int min = jsonPath.get("squad.collect{ Integer.parseInt(it.dateOfBirth.substring(0,4)) }.min()");

        System.out.println(min);

        // Find the youngest

        String oldesttName = jsonPath.get("squad.find{ it.dateOfBirth.substring(0,4) == '" + min + "'}.name");

        System.out.println(oldesttName);

        int max = jsonPath.get("squad.collect{ Integer.parseInt(it.dateOfBirth.substring(0,4)) }.max()");

        System.out.println(max);

        String youngestName = jsonPath.get("squad.find{ it.dateOfBirth.substring(0,4) == '" + max + "'}.name");

        System.out.println(youngestName);

        int sumOfAllDobs = jsonPath.get("squad.collect{ Integer.parseInt(it.dateOfBirth.substring(0,4)) }.sum()");

        int noOfPlayers = jsonPath.get("squad.size()");

        System.out.println(sumOfAllDobs/noOfPlayers);

        int averageAge = 2022 - sumOfAllDobs/noOfPlayers;

        System.out.println(averageAge);




    }
    }
