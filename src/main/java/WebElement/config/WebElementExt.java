package WebElement.config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.internal.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class WebElementExt extends RemoteWebElement  implements  WebElement, FindsByLinkText, FindsById, FindsByName, FindsByTagName, FindsByClassName, FindsByCssSelector, FindsByXPath, WrapsDriver, HasIdentity, TakesScreenshot, Locatable {
    @Autowired
    protected WebDriver driver;
    @Value("${default.timeout:30}")
    private int timeout;
    public WebElement webElement;


    private static WebElementExt webElementExt;

    WebElementExt() {
    }
    public static WebElementExt getInstance(){
        if(null==webElementExt){
            webElementExt= new WebElementExt();
            return webElementExt;
        }
        else
            return webElementExt;

    }
    public WebElement scrollTo() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        Actions action1 = new Actions(driver);
        action1.moveToElement(this).perform();
        return this;
    }
}
