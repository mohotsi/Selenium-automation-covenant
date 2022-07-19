package ae.proctected.covenant.Seleniumautomation.Webdriver;

import ae.proctected.covenant.Seleniumautomation.annotation.BrowserDriver;
import ae.proctected.covenant.Seleniumautomation.annotation.ThreadBrowsing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@BrowserDriver
@Profile("!remote")
public class WebDriverBean extends ProfileCommonShare {
    @Value("${default.timeout}")
    private int timeout;


    @Bean
    public WebDriverWait webDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, timeout);
    }
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @ThreadBrowsing
    public WebDriver fireDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    @ThreadBrowsing
    public WebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @ConditionalOnProperty(name = "browser", havingValue = "opera")
    @ThreadBrowsing
    public WebDriver operaDriver() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }

    @ThreadBrowsing
    @ConditionalOnMissingBean
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
       // options.addArguments("disable-infobars"); // disabling infobars
       // options.addArguments("--disable-extensions"); // disabling extensions
//        options.addArguments("--disable-gpu"); // applicable to windows os only
      options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
       options.addArguments("--no-sandbox"); // Bypass OS security model
  return new ChromeDriver(options);
    }


}
