package stepDefinitions;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.duotify.HomePage;
import pages.duotify.PlaylistsPage;
import pages.duotify.RegisterPage;
import utilities.DBUtility;
import utilities.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DuotifyStepDefs {

    Map<String, String>  expectedMap;

    @Given("I am on the sign up page and I am connected to the DB")
    public void iAmOnTheSignUpPageAndIAmConnectedToTheDB() {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        DBUtility.createConnection();

    }
    @When("I sign up with the following info")
    public void iSignUpWithTheFollowingInfo(List<Map<String,String>> dataTable) {


        expectedMap = dataTable.get(0);
        RegisterPage registerPage = new RegisterPage();
         registerPage.hideLogin.click();
        registerPage.username.sendKeys(expectedMap.get("username"));
        registerPage.firstName.sendKeys(expectedMap.get("first"));
        registerPage.lastName.sendKeys(expectedMap.get("last"));
        registerPage.email.sendKeys(expectedMap.get("email"));
        registerPage.email2.sendKeys(expectedMap.get("email"));
        registerPage.password.sendKeys(expectedMap.get("password"));
        registerPage.password2.sendKeys(expectedMap.get("password"));
        registerPage.registerButton.click();

    }
    @Then("I should land on the homepage")
    public void iShouldLandOnTheHomepage() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));
    }
    @Then("The database should also have the correct info")
    public void theDatabaseShouldAlsoHaveTheCorrectInfo() throws SQLException {

        String expectedUsername = expectedMap.get("username");
        String expectedFirst = expectedMap.get("first");
        String expectedLast = expectedMap.get("last");
        String expectedEmail = expectedMap.get("email");
        String expectedPass = expectedMap.get("password");

        String query  = "select * from users where username='"+expectedUsername+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

        String actualUsername = (String)(actualMap.get("username"));
        String actualFirst= (String)(actualMap.get("firstName"));
        String actualLast = (String)(actualMap.get("lastName"));
        String actualEmail = (String)(actualMap.get("email"));
        String actualPassword = (String)(actualMap.get("password"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedUsername).isEqualTo( actualUsername);
        softAssertions.assertThat(expectedFirst).isEqualTo(actualFirst);
        softAssertions.assertThat(expectedLast).isEqualTo( actualLast);
        softAssertions.assertThat(expectedEmail).isEqualTo( actualEmail);
        System.out.println(expectedEmail);
        System.out.println(actualEmail);
        String expectedPassMD5 = DigestUtils.md5Hex(expectedPass);
        softAssertions.assertThat(expectedPassMD5).isEqualTo( actualPassword);

        softAssertions.assertAll();

        DBUtility.updateQuery("delete from users where username='"+expectedUsername+"'");
        DBUtility.close();




    }


    @Given("I am connected to the DB")
    public void iAmConnectedToTheDB() {
        DBUtility.createConnection();
    }
    @When("I add a new user to the database with the following info")
    public void iAddANewUserToTheDatabaseWithTheFollowingInfo(List<Map<String,String>> dataTable) throws SQLException {
        Map<String, String> expectedMap = dataTable.get(0);

        String passwordAsMd5 = DigestUtils.md5Hex(expectedMap.get("password"));
        String query = "INSERT INTO users (username, firstName, lastName, email, password ) values ('"+expectedMap.get("username")+"', '"+expectedMap.get("first")+"', '"+expectedMap.get("last")+"', '"+expectedMap.get("email")+"', '"+passwordAsMd5+"')";

        DBUtility.updateQuery(query);


    }
    @Then("I should be able to log in with the {string} as uasername and {string} as password on the UI")
    public void iShouldBeAbleToLogInWithTheAsUasernameAndAsPasswordOnTheUI(String user, String pass) throws SQLException {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        RegisterPage registerPage = new RegisterPage();

        registerPage.usernameLog.sendKeys(user);
        registerPage.passwordLog.sendKeys(pass);
        registerPage.loginButton.click();

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));
        DBUtility.updateQuery("delete from users where username='"+user+"'");
        DBUtility.close();

    }

    List<String> actualColumnNames;
    @When("I retrieve the column names for the Songs table")
    public void iRetrieveTheColumnNamesForTheSongsTable() {
        actualColumnNames = DBUtility.getColumnNames("select * from songs limit 1");
    }
    @Then("It should be the following")
    public void itShouldBeTheFollowing(List<String> expected) {
       SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualColumnNames).isEqualTo(expected);

        DBUtility.close();
        softAssertions.assertAll();
    }


    @Given("I am logged in as a valid user")
    public void iAmLoggedInAsAValidUser() {

        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        RegisterPage registerPage = new RegisterPage();

        registerPage.usernameLog.sendKeys("minnie");
        registerPage.passwordLog.sendKeys("minnie");
        registerPage.loginButton.click();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));
    }
    String expected;
    @When("I update the user email")
    public void iUpdateTheUserEmail() {

        HomePage homePage = new HomePage();

        homePage.username.click();
        homePage.userDETAILS.click();

        expected = homePage.nameField.getAttribute("value").toLowerCase();
        homePage.nameField.clear();
        homePage.nameField.sendKeys(expected);

       homePage.saveButton.click();



    }
    @Then("The user email update should be correctly updated within the database")
    public void theUserEmailUpdateShouldBeCorrectlyUpdatedWithinTheDatabase() {

        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select email from users where username='minnie'");


       String actual = (String)(queryResultAsListOfLists.get(0).get(0));

       Assert.assertEquals(expected, actual);

        DBUtility.close();



    }
    String updatedOne;
    @When("I update username {string} email with to  {string}")
    public void iUpdateUsernameEmailWithTo(String username, String updatedEmail) throws SQLException {

        updatedOne = updatedEmail;
        DBUtility.updateQuery("update users set email='"+updatedEmail+"' where username='"+username+"'");

    }
    @Then("I should see the updated email on the UI")
    public void iShouldSeeTheUpdatedEmailOnTheUI() throws InterruptedException {
        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        RegisterPage registerPage = new RegisterPage();

        registerPage.usernameLog.sendKeys("minnie");
        registerPage.passwordLog.sendKeys("minnie");
        registerPage.loginButton.click();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));

        HomePage homePage = new HomePage();

        homePage.username.click();
        homePage.userDETAILS.click();

        String actual = homePage.nameField.getAttribute("value");

        Thread.sleep(1000);
        Assert.assertEquals(updatedOne, actual);

        DBUtility.close();

    }

    List<String> actualGenres;
    @When("I retrieve the genres from the genres table")
    public void iRetrieveTheGenresFromTheGenresTable() {
        List<List<Object>> select_name_from_genres = DBUtility.getQueryResultAsListOfLists("select name from genres");

        actualGenres = new ArrayList<>();
        for (List<Object> each : select_name_from_genres) {
            actualGenres.add((String)(each.get(0)));
        }




    }
    @Then("it should contain the following")
    public void itShouldContainTheFollowing( List<String> expectedGenres) {

        List<String> expectedGenresModifiable = new ArrayList<>(expectedGenres);

        Collections.sort(expectedGenresModifiable);
        Collections.sort(actualGenres);
        Assert.assertEquals(expectedGenresModifiable, actualGenres);
    }



    @When("I update the name column with a String with an invalid length of {int} , the update should truncate the length to {int}")
    public void iUpdateTheNameColumnWithAStringWithAnInvalidLengthOf(Integer length, Integer truncated) throws SQLException {

        String str = "";
        for (int i = 0; i < length; i++) {
          char ch =  (char)('a' + (int)(Math.random()*26));
          str += ch;
        }

        try{
            DBUtility.updateQuery("update playlists set name='"+str+"' where id=1");
        }catch (MysqlDataTruncation ex){
            ex.printStackTrace();
        }


        List<List<Object>> queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select name from playlists where id=1");
        Integer actualLength = ((String)(queryResultAsListOfLists.get(0).get(0))).length();

        Assert.assertEquals(truncated, actualLength);


    }


    String expectedDB;
    @When("I update the name column with a unicode chars, the update should be successful")
    public void iUpdateTheNameColumnWithAUnicodeCharsTheUpdateShouldBeSuccessful() throws SQLException {

        expectedDB = "日本人の氏名日本人の姓名日本人の名前";
        int result = DBUtility.updateQuery("update playlists set name='"+expectedDB+"' where id=31");

        String actual =  (String)(DBUtility.getQueryResultAsListOfLists("select name from playlists where id=31").get(0).get(0));
        Assert.assertNotEquals(0, result);
        Assert.assertEquals(expectedDB, actual);
    }

    @Then("The update should be also successful on the UI")
    public void theUpdateShouldBeAlsoSuccessfulOnTheUI() {


        Driver.getDriver().get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        RegisterPage registerPage = new RegisterPage();

        registerPage.usernameLog.sendKeys("minnie");
        registerPage.passwordLog.sendKeys("minnie");
        registerPage.loginButton.click();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().equals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?"));

        HomePage homePage = new HomePage();

        homePage.yourMusic.click();

        Driver.getDriver().navigate().refresh();// get a fresh reference to avoid StaleElementReference exception

        String actualUI = new PlaylistsPage().playlist.getText();

        Assert.assertEquals(expectedDB, actualUI);








    }



    String username1;
    String first1;
    String last1;
    String email1;
    String pass1;

    @When("I sign up with the following info {string} {string} {string} {string}  {string}")
    public void iSignUpWithTheFollowingInfo(String username, String first, String last, String email, String pass) {


         username1 = username;
         first1 = first;
         last1 = last;
         email1 = email;
         pass1 = pass;

        RegisterPage registerPage = new RegisterPage();
        registerPage.hideLogin.click();
        registerPage.username.sendKeys(username);
        registerPage.firstName.sendKeys(first);
        registerPage.lastName.sendKeys(last);
        registerPage.email.sendKeys(email);
        registerPage.email2.sendKeys(email);
        registerPage.password.sendKeys(pass);
        registerPage.password2.sendKeys(pass);
        registerPage.registerButton.click();


    }
    @Then("The database should also have the correct info without spaces")
    public void theDatabaseShouldAlsoHaveTheCorrectInfoWithoutSpaces() throws SQLException {



        String query  = "select * from users where username='"+username1.trim()+"'";
        List<Map<String, Object>> queryResultListOfMaps = DBUtility.getQueryResultListOfMaps(query);
        Map<String, Object> actualMap = queryResultListOfMaps.get(0);

        String actualUsername = (String)(actualMap.get("username"));
        String actualFirst= (String)(actualMap.get("firstName"));
        String actualLast = (String)(actualMap.get("lastName"));
        String actualEmail = (String)(actualMap.get("email"));
        String actualPassword = (String)(actualMap.get("password"));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(username1.trim()).isEqualTo( actualUsername);
        softAssertions.assertThat(first1.trim()).isEqualTo(actualFirst);
        softAssertions.assertThat(last1.trim()).isEqualTo( actualLast);
        softAssertions.assertThat(email1.trim()).isEqualTo( actualEmail);


        softAssertions.assertAll();

        DBUtility.updateQuery("delete from users where username='"+username1.trim()+"'");
        DBUtility.close();
    }

    List<List<Object>> queryResultAsListOfLists;
    @When("I send a query to check for duplicate usernames")
    public void iSendAQueryToCheckForDuplicateUsernames() {

        queryResultAsListOfLists = DBUtility.getQueryResultAsListOfLists("select username, count(*) from users group by username having count(*)>1");
    }
    @Then("The returned result list should be empty")
    public void theReturnedResultListShouldBeEmpty() {

        Assert.assertTrue("The list is not empty and the size is " + queryResultAsListOfLists.size(), queryResultAsListOfLists.isEmpty() );
    }




}
