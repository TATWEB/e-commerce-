package pages.duotify;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class RegisterPage {

    @FindBy(id = "loginUsername")
    public WebElement usernameLog;

    @FindBy(id = "loginPassword")
    public WebElement passwordLog;

    @FindBy(name = "loginButton")
    public WebElement loginButton;

    public RegisterPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "firstName")
    public WebElement firstName;


    @FindBy(id = "lastName")
    public WebElement lastName;


    @FindBy(id = "email")
    public WebElement email;


    @FindBy(id = "email2")
    public WebElement email2;


    @FindBy(id = "password")
    public WebElement password;


    @FindBy(id = "password2")
    public WebElement password2;

    @FindBy(name = "registerButton")
    public WebElement registerButton;

    @FindBy(id = "hideLogin")
    public WebElement hideLogin;

}
