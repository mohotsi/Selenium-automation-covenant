package ae.proctected.covenant.Seleniumautomation.util;

import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface ScrollToWithRetry {

    WebElement scrollTo(WebElement webElement,Integer count);
}
