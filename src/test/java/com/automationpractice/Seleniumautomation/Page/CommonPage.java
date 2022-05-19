package com.automationpractice.Seleniumautomation.Page;


import com.automationpractice.Seleniumautomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

@Page
public abstract class CommonPage {
    @Value("${default.timeout:30}")
    private int timeout;
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait waitForElement;

    @FindBy(className = "shopping_cart_link")
    WebElement shopping_cart_link;
    @FindBy(className = "cart_item")
    List<WebElement> items;
    @FindBy(className = "shopping_cart_badge")
    WebElement shopping_cart_badge;
    Action action;

    @PostConstruct
    private void initilize(){
        PageFactory.initElements(this.driver,this);
    }
    public void quit(){driver.quit();}

    public void clickRetry(WebElement webElement){

        waitForElement.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
    public WebElement waitUntilItIsDisplayed(WebElement webElement){
        waitForElement.until((e)->webElement.isDisplayed());
        return webElement;
    }
    public WebElement scrollTo(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
       return webElement;
    }
    public WebElement highlight(WebElement webElement){

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style', 'background: GreenYellow; border: GreenYellow;color:black;');", webElement);
        return webElement;
    }


}
