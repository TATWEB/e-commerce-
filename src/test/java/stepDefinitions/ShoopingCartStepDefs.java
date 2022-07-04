package stepDefinitions;

import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import pages.ShoppingCartPage;

import java.util.List;
import java.util.Map;

public class ShoopingCartStepDefs {



    @Then("The other shopping cart details should be the following")
    public void theOtherShoppingCartDetailsShouldBeTheFollowing(List<Map<String, String>> dataTable) {

        Map<String, String> expectedMap = dataTable.get(0);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(shoppingCartPage.productName.getText()).isEqualTo(expectedMap.get("Name"));
        softAssertions.assertThat(shoppingCartPage.SKU.getText().split(" ")[2]).isEqualTo(expectedMap.get("SKU"));
        softAssertions.assertThat(shoppingCartPage.unitPrice.getText()).isEqualTo(expectedMap.get("Unit Price"));
        softAssertions.assertThat(shoppingCartPage.shippingFee.getText()).isEqualTo(expectedMap.get("Shipping fee"));
        softAssertions.assertThat(shoppingCartPage.tax.getText()).isEqualTo(expectedMap.get("Tax"));

        softAssertions.assertAll();


    }
}
