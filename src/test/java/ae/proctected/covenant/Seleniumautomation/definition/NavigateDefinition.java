package ae.proctected.covenant.Seleniumautomation.definition;

import ae.proctected.covenant.Seleniumautomation.Page.CommonPage;
import ae.proctected.covenant.Seleniumautomation.Page.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NavigateDefinition {
    @Autowired
    @Lazy
    HomePage homePage;

    @Autowired
    @Lazy
    CommonPage commonPage;
    @Lazy
    @Autowired
    ApplicationContext applicationContext;

    @When("User Navigate to")
    public void userNavigateTo(DataTable dataTable) {
        dataTable.asMaps().stream().map(l -> l.get("link")).forEach(link ->
                homePage.navigateTo(link)
        );

    }

    @Then("verify user is on {string} page")
    public void verifyUserIsOnPage(String title) throws InterruptedException {
        Thread.sleep(7000);

        assertThat(title + "user didn't successfully navigate to", applicationContext.getBean(WebDriver.class)
                .getTitle(), equalTo(title));
    }
}
