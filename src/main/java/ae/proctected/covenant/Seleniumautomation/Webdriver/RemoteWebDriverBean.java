package ae.proctected.covenant.Seleniumautomation.Webdriver;


import ae.proctected.covenant.Seleniumautomation.annotation.BrowserDriver;
import ae.proctected.covenant.Seleniumautomation.annotation.ThreadBrowsing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import java.net.URL;

@BrowserDriver
@Profile("remote")
public class RemoteWebDriverBean extends ProfileCommonShare {

    @Value("${selenium.grid.url}")
    private URL url;

    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @ThreadBrowsing
    public WebDriver fireDriver() {
        return new RemoteWebDriver(url, DesiredCapabilities.firefox());
    }

    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    @ThreadBrowsing
    public WebDriver edgeDriver() {
        return new RemoteWebDriver(url, DesiredCapabilities.edge());
    }

    @ConditionalOnProperty(name = "browser", havingValue = "opera")
    @ThreadBrowsing
    public WebDriver operaDriver() {
        return new RemoteWebDriver(url, DesiredCapabilities.opera());
    }

    @ThreadBrowsing
    @ConditionalOnMissingBean
    public WebDriver chromeDriver() {
        return new RemoteWebDriver(url, DesiredCapabilities.chrome());
    }

}
