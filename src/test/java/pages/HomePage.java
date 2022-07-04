package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomePage {


    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "search_query_top")
    public WebElement searchBox;


    @FindBy(xpath = "//ul[@id='homefeatured']//a[@class='product-name']")
    public List<WebElement> allPopularProducts;





    public void clickOnProductLink(String product){
        String xpath = "//ul[@id='homefeatured']//a[@title='"+product+"'][@class='product-name']";
        Driver.getDriver().findElement(By.xpath(xpath)).click();

    }




}
