package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class CartWindowLayerComponent extends CommonPage {


    @FindBy(xpath = "//*[contains(.,'Continue shopping')]")
    private WebElement continueShopping;

    public void clickContinueShopping() {
        clickRetry(continueShopping);
    }
}
