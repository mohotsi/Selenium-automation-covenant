package ae.proctected.covenant.Seleniumautomation.definition;

import ae.proctected.covenant.Seleniumautomation.Page.Component.NavigationComponent;
import ae.proctected.covenant.Seleniumautomation.Page.LaunchersPage;
import ae.proctected.covenant.Seleniumautomation.Page.ListenersPage;
import ae.proctected.covenant.Seleniumautomation.Page.PrivacyPage;
import ae.proctected.covenant.Seleniumautomation.util.CommunicationProtocol;
import com.jcraft.jsch.JSchException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
    CommunicationProtocol communicationProtocol;

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
}
