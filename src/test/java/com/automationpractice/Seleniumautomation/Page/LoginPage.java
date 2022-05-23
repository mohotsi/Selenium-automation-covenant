package com.automationpractice.Seleniumautomation.Page;

import com.automationpractice.Seleniumautomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class LoginPage extends CommonPage {

    @FindBy(id = "email_create")
    WebElement CreateEmailAddress;
    @FindBy(id = "SubmitCreate")
    WebElement createAnAccountButton;
    @FindBy(id = "email")
    WebElement loginEmailAddress;
    @FindBy(id = "passwd")
    WebElement password;
    @FindBy(id = "SubmitLogin")
    WebElement signInButton;

    public void signInIntoWebSite(String email, String password) {
        scrollTo(loginEmailAddress).sendKeys(email);
        this.password.sendKeys(password);
        clickRetry(signInButton);

    }


}
