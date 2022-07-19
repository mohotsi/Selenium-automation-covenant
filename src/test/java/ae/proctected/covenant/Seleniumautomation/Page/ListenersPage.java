package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class ListenersPage extends CommonPage{

    @FindBy(xpath = "//a[contains(.,'Create')]")
    private WebElement create;

    @FindBy(xpath = "//button[contains(.,'Create')]")
    private WebElement createSubmit;
    @FindBy(id = "Name")
    private WebElement name;




    public String createListenersWithDefaultValues(){
     clickRetry(scrollTo(create));
     val name=this.name.getText();
     clickRetry(scrollTo(createSubmit));
     return name;
    }

}
