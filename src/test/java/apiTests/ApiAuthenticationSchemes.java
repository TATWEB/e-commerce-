package apiTests;

import org.junit.Test;
import java.util.Base64;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiAuthenticationSchemes {



    @Test
    public void basicAuthExample(){

        baseURI = "https://postman-echo.com";

        String base64String = Base64.getEncoder().encodeToString(("postman:password").getBytes());

        System.out.println(base64String);

        given().
//                auth().basic("postman", "password").    // this is the easiest way
        header("Authorization", "Basic " + base64String). // another option is to send it as a header, dont forget the space after Basic
        when().log().all().
                get("/basic-auth").
        then().log().all().
                statusCode(200).
                body("authenticated", is(true));

    }



}
