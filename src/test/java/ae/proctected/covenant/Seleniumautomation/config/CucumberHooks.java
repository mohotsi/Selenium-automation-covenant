package ae.proctected.covenant.Seleniumautomation.config;

import ae.proctected.covenant.Seleniumautomation.Page.PrivacyPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

public class CucumberHooks {
    @Lazy
    @Autowired
    ApplicationContext applicationContext;
    @Lazy
    @Autowired
    WebDriver driver;

    @Value("${application.url}")
    private String url;


    @Before
    public void openGUILink() {
        applicationContext.getBean(WebDriver.class).get(url);



    }

    @AfterStep
    public void afterTest(Scenario scenario) {

        if (scenario.isFailed())
            scenario.attach(applicationContext.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
    }

    @After
    public void afterscenari() {

       driver.manage().deleteAllCookies();
      driver.quit();

    }
}
