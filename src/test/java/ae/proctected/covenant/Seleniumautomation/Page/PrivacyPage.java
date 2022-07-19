package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Page
public class PrivacyPage extends CommonPage{

    @FindBy(id = "details-button")
    private WebElement advanced;
    @FindBy(id = "proceed-link")
    private WebElement proceedLink;

    public void proceedToLogin(){
        try {
            clickRetry(advanced);
            clickRetry(proceedLink);
        }
        catch (Exception e){

        }
    }

}
