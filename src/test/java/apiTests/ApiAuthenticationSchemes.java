package apiTests;

import org.junit.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ApiAuthenticationSchemes {



    @Test
    public void basicAuthExample(){

        baseURI = "https://postman-echo.com";

        String base64String = Base64.getEncoder().encodeToString(("postman:password").getBytes());

        System.out.println(base64String);

        given().
//                auth().basic("postman", "password").
        header("Authorization", "Basic " + base64String).
        when().log().all().
                get("/basic-auth").
        then().log().all().
                statusCode(200).
                body("authenticated", is(true));

    }



}
