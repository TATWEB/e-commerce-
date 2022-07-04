package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ShoppingCartPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class ProductDetailsStepDefs {

    String productNameAnother;
    Integer quantityExp;
    String expectedSizeClass;
    String expectedColorClass;
    @When("I click on {string}")
    public void i_click_on(String productName) throws InterruptedException {
        productNameAnother = productName;
        new HomePage().clickOnProductLink(productName);

    }
    @Then("I should land on the product details page")
    public void i_should_land_on_the_product_details_page() {
        Assert.assertEquals(productNameAnother + " - My Store", Driver.getDriver().getTitle());
    }
    @Then("The default quantity should be {int}")
    public void the_default_quantity_should_be(Integer quantity) {
          Assert.assertEquals(String.valueOf(quantity), new ProductDetailsPage().defaultQuantity.getAttribute("value"));
    }

    @When("I click on {word} button {int} times")
    public void i_click_on_plus_button_times(String plusOrMinus, Integer times) {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        for (int i = 0; i < times; i++) {
            if(plusOrMinus.equals("plus"))
            productDetailsPage.plusButton.click();
            else
                productDetailsPage.minusButton.click();
        }

    }


    @Then("The price should be {double}")
    public void the_price_should_be(Double expectedPrice) {
       Double actualPrice = Double.valueOf(new ProductDetailsPage().price.getText().substring(1));

       Assert.assertEquals(expectedPrice, actualPrice);
    }





    @Then("The details of the product should be the following")
    public void theDetailsOfTheProductShouldBeTheFollowing(List<Map<String,String>> dataTable) {

        Map<String, String> expectedData = dataTable.get(0);


        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        //JUnit does NOT have any built in soft assertions
        // Instead we can AssertJ library that offers SoftAssertions class

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(expectedData.get("name")).isEqualTo(productDetailsPage.productName.getText() );

        softAssertions.assertThat(expectedData.get("price")).isEqualTo(productDetailsPage.price.getText().substring(1));
        softAssertions.assertThat(expectedData.get("model")).isEqualTo( productDetailsPage.model.getText() );
        softAssertions.assertThat(expectedData.get("condition")).isEqualTo( productDetailsPage.condition.getText());
        softAssertions.assertThat(expectedData.get("default_quantity")).isEqualTo( productDetailsPage.defaultQuantity.getAttribute("value"));
        softAssertions.assertThat(expectedData.get("default_size")).isEqualTo( new Select(new ProductDetailsPage().defaultSize).getFirstSelectedOption().getText() );

         softAssertions.assertAll();


    }


    @Then("The title of the page should contain {string}")
    public void theTitleOfThePageShouldContain(String string) {
      Assert.assertTrue(Driver.getDriver().getTitle().contains(string));
    }


    @When("I increase the quantity to {int} and choose size {string} and color {string} and click on add to cart button")
    public void iIncreaseTheQuantityToAndChooseSizeAndColor(Integer quantity, String expectedSize, String expectedColor) throws InterruptedException {

        quantityExp = quantity;
        expectedSizeClass =  expectedSize;
        expectedColorClass = expectedColor;
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        for (int i = 0; i < quantity-1; i++) {
            productDetailsPage.plusButton.click();
        }




        productDetailsPage.chooseSize(expectedSize);
        productDetailsPage.chooseColor(expectedColor);

        productDetailsPage.addToCartButton.click();


//




    }

    @Then("The quantity, size and color should be correct")
    public void theQuantitySizeAndColorShouldBeCorrect() throws InterruptedException {





        String actualQuantityWithExtra = new ShoppingCartPage().quantity.getText();

        String actualQuantity = "";
        for (int i = 0; i < actualQuantityWithExtra.length(); i++) {
              if(Character.isDigit(actualQuantityWithExtra.charAt(i))){
                actualQuantity+= actualQuantityWithExtra.charAt(i);
            }
        }
         // The code to extract the price out of the whole String


        Assert.assertEquals(quantityExp, Integer.valueOf(actualQuantity));

        String sizeAndColor = new ShoppingCartPage().sizeAndColor.getText();
        String actualSize = sizeAndColor.substring(sizeAndColor.length()-1);
        Assert.assertEquals(expectedSizeClass, actualSize);

        String actualColor = sizeAndColor.split(" ")[2].replace(",", "");
        Assert.assertEquals(expectedColorClass, actualColor);
        Thread.sleep(2000);


    }


    @When("I click on Proceed to Checkout")
    public void iClickOnProceedToCheckout() throws InterruptedException {
        Thread.sleep(3000);
       new ProductDetailsPage().proceedButton.click();
       Thread.sleep(2000);

    }


    @Then("The product price should be {double}")
    public void theProductPriceShouldBe(Double expected) {
       Assert.assertEquals(expected, Double.valueOf(new ProductDetailsPage().price.getText().substring(1)));
    }

}
