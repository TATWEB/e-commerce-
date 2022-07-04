package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SearchPage {

    public SearchPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }




    @FindBy(xpath = "//span[@class='lighter']")
    public WebElement searchTerm;

    @FindBy(className = "alert-warning")  // classname can ONLY be used with a single class name
                                          //alert alert-warning  does not work
    public WebElement alert;



}
