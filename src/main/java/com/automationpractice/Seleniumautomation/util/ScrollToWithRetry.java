package com.automationpractice.Seleniumautomation.util;

import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface ScrollToWithRetry {

    WebElement scrollTo(WebElement webElement,Integer count);
}
