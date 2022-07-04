package apiTests;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Driver;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MultiLayerFlowTests {




    @Test
    public void apiToUIFlowTest(){

        // Create a new user through API call
        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";

       String email = new Faker().internet().emailAddress();
       String pass = "magdalena2020";
        given().
                body("{\n" +
                        "\"first_name\" : \"Magdalena\",\n" +
                        "\"last_name\" : \"Nitek\",\n" +
                        "\"email\" : \""+email +"\",\n" +
                        "\"password\" : \""+pass+"\"\n" +
                        "}").
                when().log().all().
                        post("/register.php").
                then().log().all().
                statusCode(200).
                body("message",equalTo("You have successfully registered."));

        // Verify the user creation on the UI by logging in

        Driver.getDriver().get("http://qa-duobank.us-east-2.elasticbeanstalk.com/");
        Driver.getDriver().findElement(By.id("exampleInputEmail1")).sendKeys(email);
        Driver.getDriver().findElement(By.id("exampleInputPassword1")).sendKeys(pass + Keys.ENTER);

        Assert.assertEquals("Loan Application", Driver.getDriver().getTitle());



    }

}
