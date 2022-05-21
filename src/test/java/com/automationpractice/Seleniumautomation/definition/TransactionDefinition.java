package com.automationpractice.Seleniumautomation.definition;

import com.automationpractice.Seleniumautomation.Page.CartPage;
import com.automationpractice.Seleniumautomation.Page.CartWindowLayerComponent;
import com.automationpractice.Seleniumautomation.Page.HomePage;
import com.automationpractice.Seleniumautomation.model.ProductData;
import com.automationpractice.Seleniumautomation.model.WebTableRow;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.automationpractice.Seleniumautomation.util.ImageReader.getImageFromUrl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TransactionDefinition {
    @Autowired
    @Lazy
    HomePage homePage;
    @Autowired
    @Lazy
    CartWindowLayerComponent cartWindowLayerComponent;
    @Autowired
    @Lazy
    CartPage cartPage;
    ArrayList<ProductData> productsDataAddedToCart = new ArrayList<>();
    @Lazy
    @Autowired
    ApplicationContext applicationContext;

    @When("User Add an item\\(s) to shopping your cart.")
    public void userAddAnItemSToShoppingYourCart(DataTable dataTable) {
        dataTable.asMaps().forEach(product ->
                {
                    try {
                        productsDataAddedToCart.add(homePage.extractProductData(product.get("Item")));
                        homePage.addItemToShoppingCart(product.get("Item"));
                        cartWindowLayerComponent.clickContinueShopping();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
        );

    }

    @Then("navigate to cart")
    public void navigateToCart() {

        homePage.navigateToCart();

    }

    @Then("Verify that the Item\\(s) displayed on cart are the one that were added")
    public void verifyThatTheItemSDisplayedOnCartAreTheOneThatWereAdded() {
        val cartSummary = cartPage.getShoppingCartSummary();
        val actual = cartSummary.stream().map(row -> new WebTableRow(row)).
                map(r -> {
                    try {
                        val name = r.getColumnData("Description").findElement(By.tagName("a")).getText();
                        val src = r.getColumnData("Product").findElement(By.tagName("img")).getAttribute("src");
                        val image = getImageFromUrl(src);
                        val price = Double.parseDouble(r.getColumnData("Unit price").
                                findElement(By.className("price")).getText().replaceAll("\\$((\\.|\\d)+).*", "$1"));
                        return new ProductData(name, image, price);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

        val expected = productsDataAddedToCart.stream().collect(Collectors.toList());
        /**
         * The image on home page is not the same as the one on cart page so we exclude it when comparing
         */
        assertTrue("data on cart is not the same one that was added", actual.stream().map(ProductData::getName)
                .collect(Collectors.toSet())
                .equals(expected.stream().map(ProductData::getName).collect(Collectors.toSet())) &&
                actual.stream().map(ProductData::getPriceValue).collect(Collectors.toSet())
                        .equals(actual.stream().map(ProductData::getPriceValue).collect(Collectors.toSet()))

        );


    }

    @When("Set quantity amount to {string} for product {string}")
    public void setQuantityAmountToForProduct(String amount, String productName) {

    }


    @When("Set quantity amount to {string} for product {string}; verify displayed total matches calculated total.")
    public void setQuantityAmountToForProductVerifyDisplayedTotalMatchesCalculatedTotal(String amount, String productName) throws NoSuchFieldException, InterruptedException {
        val cartSummary = cartPage.getShoppingCartSummary();
        val productRow = cartPage.getProductRow(productName);
        val driver = applicationContext.getBean(WebDriver.class);
        Actions actions = new Actions(driver);


        val amountInput = productRow.getColumnData("Qty").findElement(By.className("cart_quantity_input"));
        amountInput.clear();
        amountInput.sendKeys(amount);
        actions.moveToElement(productRow.getColumnData("Qty")).doubleClick().perform();
        productRow.getColumnData("Qty").click();//click awau from the input


        Double totalCalculated = Integer.parseInt(amount) * Double.parseDouble(
                productRow.getColumnData("Unit price").findElement(By.className("price")).getText().replaceAll("\\$((\\.|\\d)+).*", "$1"));
        Thread.sleep(5000);
        val actualTotal = Double.parseDouble(cartPage.getProductRow(productName).getColumnData("Total").getText().
                replaceAll("\\$((\\.|\\d)+).*", "$1"));
        assertThat("actual total is not equal to calculated total ", actualTotal, equalTo(totalCalculated));


    }


}
