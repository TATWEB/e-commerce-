package pages;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.Driver;

public class ProductDetailsPage {


    @FindBy(xpath = "//span[@itemprop='sku']")
    public WebElement model;


    @FindBy(xpath = "//p[@id='product_condition']//span[@class='editable']")
    public WebElement condition;


    @FindBy(tagName = "h1")
    public WebElement productName;


    @FindBy(id = "group_1")
    public WebElement defaultSize;


    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    public WebElement proceedButton;


    @FindBy(xpath = "//tr[@class='odd']/td[2]")
    public WebElement composition;

    @FindBy(xpath = "//tr[@class='even']/td[2]")
    public WebElement style;

    public ProductDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(xpath = "//button[@name='Submit']")
    public WebElement addToCartButton;

    @FindBy(id = "quantity_wanted")
    public WebElement defaultQuantity;

    @FindBy(xpath = "//i[@class='icon-plus']")
    public WebElement plusButton;

    @FindBy(xpath = "//i[@class='icon-minus']")
    public WebElement minusButton;

    @FindBy(id = "our_price_display")
    public WebElement price;


    public void chooseSize(String size){
       new Select( Driver.getDriver().findElement(By.id("group_1"))).selectByVisibleText(size);
    }


    //a[@title='Blue']

    public void chooseColor(String color){
        Driver.getDriver().findElement(By.xpath("//a[@title='"+color+"']")).click();
    }


}
