package ae.proctected.covenant.Seleniumautomation.Page.Component;

import ae.proctected.covenant.Seleniumautomation.Page.CommonPage;
import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Page
public class NavigationComponent extends CommonPage {

    @FindBy(xpath = "//*[@class='sidebar-sticky']/ul/li")
    private List<WebElement> verticalNav;


    public void navigateTo(String linkText) throws InterruptedException {
waitForloadingOfWebElement(verticalNav.stream().filter(webElement -> webElement.getText().equalsIgnoreCase(linkText)).
        findFirst().get());
     waitForElement.until((e)-> verticalNav.size()>0);


       clickRetry( waitForloadingOfWebElement(verticalNav.stream().filter(webElement -> webElement.getText().equalsIgnoreCase(linkText)).
               findFirst().get()));
    }

}

