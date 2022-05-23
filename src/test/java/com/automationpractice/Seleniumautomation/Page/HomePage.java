package com.automationpractice.Seleniumautomation.Page;


import com.automationpractice.Seleniumautomation.Page.Component.Products;
import com.automationpractice.Seleniumautomation.annotation.Page;
import com.automationpractice.Seleniumautomation.model.ProductData;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.automationpractice.Seleniumautomation.util.ImageReader.getImageFromUrl;

@Page
public class HomePage extends CommonPage {

    @FindBy(xpath = "//ul[contains(@class,'sf-menu')]")
    WebElement genericMenu;
    @FindBy(id = "search_query_top")
    private WebElement searchField;
    @FindBy(name = "submit_search")
    private WebElement searchButton;
    @FindBy(className = "product-container")
    private List<WebElement> products;
    @FindBy(className = "login")
    private WebElement signIn;
    @FindBy(tagName = "a")
    private List<WebElement> links;
    @FindBy(xpath = "//*[@title='View my shopping cart']")
    private WebElement shopping_cart_badge;

    public void navigateTo(String linksText) {
        val menuLink = genericMenu.findElements(By.tagName("li"))
                .stream().filter(li -> li.getText().equalsIgnoreCase(linksText)).findFirst().get();

        scrolling(menuLink);
    }

    private void scrolling(WebElement menuLink) {
        val childMenu = menuLink.findElements(By.tagName("ul"));
        if (childMenu.size() > 0) {
            scrollTo(menuLink);
            Actions action1 = new Actions(driver);
            action1.moveToElement(menuLink).perform();
        } else {
            clickRetry(menuLink);
        }
    }

    public void navigateToCart() {

        clickRetry(shopping_cart_badge);
        driver.navigate().refresh();
    }


    public void performASearchCriteria(String textToSearch) {
        waitUntilItIsDisplayed(searchField);
        searchField.clear();
        searchField.sendKeys(textToSearch);
        clickRetry(searchButton);
    }

    public void addItemToShoppingCart(String itemName) {
        waitUntilItIsDisplayed(products.stream().findFirst().get());
        val item = products.stream().
                filter(product ->
                        product.findElement(By.className("product-name")).getText().equalsIgnoreCase(itemName))
                .findFirst().get();
        scrollTo(item);


        clickRetry(item.findElement(By.partialLinkText("Add to cart")));


    }

    public ProductData extractProductData(String itemName) throws Exception {
        waitUntilItIsDisplayed(products.stream().findFirst().get());
        val item = products.stream().
                filter(product ->
                        product.findElement(By.className("product-name")).getText().equalsIgnoreCase(itemName))
                .findFirst().get();
        scrollTo(item);
     
        waitUntilItIsDisplayed(item);

        val priceValue = Double.parseDouble(item.findElement(By.className("price"))
                .getAttribute("innerHTML").replaceAll("(\t|\n)*\\$?", ""));
        val image = getImageFromUrl(item.findElement(By.tagName("img")).getAttribute("src"));
        val name = item.findElement(By.className("product-name")).getText();
        val results = new ProductData(name, image, priceValue);
        return results;
    }


    public void navigateToSignIn() {
        clickRetry(signIn);
    }

    public List<WebElement> getLinks() {
        waitUntilItIsDisplayed(links.stream().findFirst().get());
        return links;
    }

    public Products getProducts() {
        return new Products(products);
    }


}
