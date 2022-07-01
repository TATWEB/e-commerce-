package apiTests;

import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GoogleMapsTests {


    @Test
    public void testGETPlaces(){
         baseURI = "https://maps.googleapis.com/maps/api/place";


        String responseBody = given().
                queryParam("fields", "geometry").
                queryParam("input", "Mike's American Grill").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
        when().log().all().
                get("/findplacefromtext/json").
        then().log().all().
                statusCode(200).
                body("status", equalTo("OK")).
                time(lessThan(2000L)).
                extract().asString();

        System.out.println(responseBody);

        // Send a PUT request to update the details
        // It requires the place id from the previous request.


    }
}
