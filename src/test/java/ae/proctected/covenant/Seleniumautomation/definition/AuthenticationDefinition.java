package ae.proctected.covenant.Seleniumautomation.definition;

import ae.proctected.covenant.Seleniumautomation.Page.LoginPage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AuthenticationDefinition {
    @Autowired
    @Lazy
    LoginPage loginPage;


    @When("Login into covenant with userName {string} and password {string}")
    public void loginIntoCovenantWithUserNameAndPassword(String userName, String password) {
        loginPage.signInIntoWebSite(userName,password);
    }
}
