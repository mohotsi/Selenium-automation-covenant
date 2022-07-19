package ae.proctected.covenant.Seleniumautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/ae/proctected/covenant/Seleniumautomation/feature"
        , glue = {"ae.proctected.covenant.Seleniumautomation.definition", "ae.proctected.covenant.Seleniumautomation.config"},
        monochrome = true,
        plugin = {"pretty",
                "json:target/output/HtmlReports.json", "html:target/output/HtmlReports.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
