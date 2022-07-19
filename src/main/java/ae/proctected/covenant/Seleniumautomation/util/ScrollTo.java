package ae.proctected.covenant.Seleniumautomation.util;

import org.openqa.selenium.WebElement;

import java.util.Optional;

@FunctionalInterface
public interface ScrollTo<T extends WebElement> {

    WebElement scrollTo(WebElement webElement);
}
