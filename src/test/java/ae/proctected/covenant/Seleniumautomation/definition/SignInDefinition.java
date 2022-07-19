package ae.proctected.covenant.Seleniumautomation.definition;

import ae.proctected.covenant.Seleniumautomation.Page.HomePage;
import ae.proctected.covenant.Seleniumautomation.Page.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.testng.AssertJUnit.assertTrue;

public class SignInDefinition {

    @Autowired
    @Lazy
    LoginPage loginPage;
    @Autowired
    @Lazy
    HomePage homePage;


    @When("User login with the email {string} and password {string}")
    public void userLoginWithTheEmailAndPassword(String email, String password) throws InterruptedException {
        homePage.navigateToSignIn();
        loginPage.signInIntoWebSite(email, password);
    }

    @Then("Verify the following text links are displayed")
    public void verifyTheFollowingTextLinksAreDisplayed(DataTable dataTable) throws InterruptedException {
        Thread.sleep(1000*60);
        val expected = dataTable.asMaps().stream().map(col -> col.get("links")).collect(Collectors.toList());
        val actual = homePage.getLinks().stream().map(WebElement::getText).collect(Collectors.toList());

        assertTrue("User was not successfully login", actual.containsAll(expected));

    }


    @Given("One plus two")
    public void onePlusTwo() throws Exception {
        int i=0;
    Thread.sleep(2010101);
    System.exit(0);
    }
}
