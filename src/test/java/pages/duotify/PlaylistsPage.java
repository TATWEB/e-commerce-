package pages.duotify;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PlaylistsPage {


    public PlaylistsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//div[@class='gridViewInfo']")
    public WebElement playlist;
}
