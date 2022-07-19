package ae.proctected.covenant.Seleniumautomation.Page;



import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Page
public class CommonPage  {
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait waitForElement;
    @FindBy(tagName="a")
    WebElement downloadItemURL;


    @Value("${default.timeout}")
    protected int timeout;

    @PostConstruct
    private void initilize() {
        PageFactory.initElements(this.driver, this);
    }

    public void quit() {
        driver.quit();
    }


    public void select(WebElement menu,String itemText){
        val select= new Select(menu);
        select.selectByVisibleText(itemText);
    }

    public void clickRetry(final WebElement webElement) {

        waitForElement.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }
    public WebElement scrollTo(final WebElement webElement) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waitForElement.ignoring(ElementNotInteractableException.class).until((e)->webElement.isDisplayed());
        Actions action1 = new Actions(driver);
        action1.moveToElement(webElement).perform();
        return webElement;
    }

    public WebElement waitUntilItIsDisplayed(WebElement webElement) {
        waitForElement.until((e) -> webElement.isDisplayed());
        return webElement;
    }

    protected WebElement waitForloadingOfWebElement(final WebElement webElement) throws InterruptedException {
        int count=timeout;
      return   waitForElementNotToBeStale(webElement,count);
    }
    private WebElement waitForElementNotToBeStale(final WebElement webElement, int count)  {
        try {
            return webElement;
        } catch (StaleElementReferenceException e) {
            if (count > 0) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
               return waitForElementNotToBeStale(webElement, count - 1);
            } else
                return webElement;

        }
    }



    public WebElement highlight(WebElement webElement) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: GreenYellow; border: GreenYellow;color:black;');", webElement);
        return webElement;
    }








}
