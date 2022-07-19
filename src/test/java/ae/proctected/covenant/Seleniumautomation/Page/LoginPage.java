package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.stream.Collectors;

@Page
public class LoginPage extends CommonPage {





    @FindBy(id = "CovenantUserRegister_UserName")
    WebElement userName;
    @FindBy(id = "CovenantUserRegister_Password")
    WebElement password;
    @FindBy(tagName = "button")
    WebElement signInButton;

    @FindBy(id = "CovenantUserRegister_ConfirmPassword")
    WebElement confirmPassword;

    public void signInIntoWebSite(String userName, String password) {
        scrollTo(this.userName).sendKeys(userName);
        this.password.sendKeys(password);
//        if(!driver.findElements(By.tagName("h1")).stream().filter(webElement -> webElement.getText()
//                .equalsIgnoreCase("Register Initial User")).collect(Collectors.toList()).isEmpty())
           // confirmPassword.sendKeys(password);
        clickRetry(signInButton);

    }


}
