package pages.duotify;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {


    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "nameFirstAndLast")
    public WebElement username;

    @FindBy(xpath = "//button[.='USER DETAILS']")
    public WebElement userDETAILS;

    @FindBy(xpath = "//button[.='SAVE']")
    public WebElement saveButton;

    @FindBy(name = "email")
    public WebElement nameField;



    @FindBy(xpath = "//span[.='Your Music']")
    public WebElement yourMusic;








}
