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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.automationpractice.Seleniumautomation.util.ImageReader.getImageFromUrl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
    @When("User Add an item\\(s) to shopping your cart.")
    public void userAddAnItemSToShoppingYourCart(DataTable dataTable) {
     dataTable.asMaps().forEach(product->
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
    public void navigateToCart()  {

        homePage.navigateToCart();

    }

    @Then("Verify that the Item\\(s) displayed on cart are the one that were added")
    public void verifyThatTheItemSDisplayedOnCartAreTheOneThatWereAdded() {
      val cartSummary=cartPage.getShoppingCartSummary();
     val actual= cartSummary.stream().map(row->new WebTableRow(row)).
              map(r->{
                  try {
                  val name=r.getColumnData("Description").findElement(By.tagName("a")).getText();
                  val src=r.getColumnData("Product").findElement(By.tagName("img")).getAttribute("src");
                 val image=getImageFromUrl(src);
                  val price=Double.parseDouble(r.getColumnData("Unit price").
                          findElement(By.className("price")).getText().replaceAll("\\$(.*?)\\s.*","$1"));
                 return new ProductData(name,image,price);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return null;
              }).collect(Collectors.toList());

     val expected=productsDataAddedToCart.stream().collect(Collectors.toList());
int i=0;
        assertTrue("User was not successfully login",actual.stream().map(ProductData::getName)
                .collect(Collectors.toSet())
                        .equals(expected.stream().map(ProductData::getName).collect(Collectors.toSet())) &&
                actual.stream().map(ProductData::getPriceValue).collect(Collectors.toSet())
                        .equals(actual.stream().map(ProductData::getPriceValue).collect(Collectors.toSet()))

                );



    }
}
