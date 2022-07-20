package ae.proctected.covenant.Seleniumautomation.definition;

import ae.proctected.covenant.Seleniumautomation.Page.Component.GruntsIteratePowerShellComponent;
import ae.proctected.covenant.Seleniumautomation.Page.Component.NavigationComponent;
import ae.proctected.covenant.Seleniumautomation.Page.GruntsPage;
import ae.proctected.covenant.Seleniumautomation.Page.LaunchersPage;
import ae.proctected.covenant.Seleniumautomation.Page.ListenersPage;
import ae.proctected.covenant.Seleniumautomation.Page.PrivacyPage;
import ae.proctected.covenant.Seleniumautomation.util.CommunicationProtocol;
import com.jcraft.jsch.JSchException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Grant {

    @Autowired
    @Lazy
    PrivacyPage privacyPage;

    @Autowired
    @Lazy
    NavigationComponent navigationComponent;

    @Autowired
    @Lazy
    ListenersPage listenersPage;

    @Autowired
    @Lazy
    LaunchersPage launchersPage;

    @Autowired
    @Lazy
    GruntsPage gruntsPage;

    @Autowired
    @Lazy
    GruntsIteratePowerShellComponent gruntsIteratePowerShellComponent;
    @Autowired
    @Lazy
    CommunicationProtocol communicationProtocol;
    @Lazy
    @Autowired
    ApplicationContext applicationContext;
    @Value("${application.url}")
    private String url;
    private String launcherFile;
    @Given("Proceed to login page")
    public void proceed_to_login_page() {
        privacyPage.proceedToLogin();
    }
    @And("Navigate to {string}")
    public void navigateTo(String linkText) throws InterruptedException {
        Thread.sleep(2000);
      navigationComponent.navigateTo(linkText);
    }

    @And("Create a Listener")
    public void createAListener() {
        listenersPage.createListenersWithDefaultValues();

    }

    @And("select Launcher {string}")
    public void selectLauncher(String launcher) {
        launchersPage.clickLauncher(launcher);
    }

    @And("Download launcher")
    public void downloadLauncher() throws InterruptedException {

       launcherFile= launchersPage.downloadLauncher();
    }

    @And("Execute the launcher file on the target machine")
    public void executeTheLauncherFileOnTheTargetMachine() throws JSchException, IOException, InterruptedException {
        communicationProtocol.powershellSSH2("Powershell -Command \""+launcherFile+"\"");


        System.out.println("Thank you jesus");
    }

    @And("click top Grunt entry")
    public void clickTopGruntEntry() throws InterruptedException {

        gruntsPage.clickTopGrunt();
    }

    @And("Navigate to Home Page")
    public void navigateToHomePage() {
        applicationContext.getBean(WebDriver.class).navigate().to(url);
    }

    @And("select grunt tab interact-tab")
    public void selectGruntTabInteractTab() {
        gruntsPage.clickInteractTab();
    }

    @And("Send command\\(s)")
    public void sendCommandS(DataTable dataTable) {
    dataTable.asMaps().stream().map(t->t.get("commands")).collect(Collectors.toList())
            .stream().forEach(command->gruntsPage.sendACommand(command));
    }

    @Then("command response are")
    public void commandResponseAre(DataTable dataTable) {
      val expected=  dataTable.asMaps().stream().map(t->t.get("responses")).collect(Collectors.toList());
      val actual=gruntsIteratePowerShellComponent.getTaskResponses();
        assertThat("commands response are wrong",actual,equalTo(expected));
    }
}
