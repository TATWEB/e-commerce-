package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ShoppingCartPage {

    public ShoppingCartPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }




    @FindBy(id = "summary_products_quantity")
    public WebElement quantity;

    @FindBy(xpath = "//td//a[contains(text(), 'Color')]")
    public WebElement sizeAndColor;

    @FindBy(xpath = "//tr//p[@class='product-name']//a")
    public WebElement productName;

    @FindBy(xpath = "//small[@class='cart_ref']")
    public WebElement SKU;

    @FindBy(xpath = "//td[@data-title='Unit price']//span[contains(@class, 'price')][not(@id)]")
    public WebElement unitPrice;

    @FindBy(id = "total_shipping")
    public WebElement shippingFee;

    @FindBy(id = "total_tax")
    public WebElement tax;





}
