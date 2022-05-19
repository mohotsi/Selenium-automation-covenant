package com.automationpractice.Seleniumautomation.Page;

import com.automationpractice.Seleniumautomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
@Page
public class CartWindowLayerComponent extends CommonPage{


    @FindBy(xpath = "//*[contains(.,'Continue shopping')]")
    private WebElement continueShopping;

    public void clickContinueShopping(){
        clickRetry(continueShopping);
    }
}
