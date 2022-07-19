package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import ae.proctected.covenant.Seleniumautomation.model.WebTableCell;
import ae.proctected.covenant.Seleniumautomation.model.WebTableRow;
import com.google.common.collect.Streams;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Page
public class LaunchersPage extends CommonPage {


    @FindBy(tagName = "table")
    private WebElement table;
    @FindBy(id = "generate")
    private WebElement generate;
    @FindBy(id="download")
    private WebElement download;






    private List<List<WebTableCell>> getTable()  {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForElement.until((e)->js.executeScript("return document.readyState").toString().equals("complete"));

        val tableHeaders =   this.table.findElements(By.tagName("th"));
        waitForElement.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(table.findElement(By.tagName("tbody")).findElement(By.tagName("tr")))));
        val tableRow = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        return tableRow.stream().map(tr -> tr.findElements(By.tagName("td")))
                .map(tr -> Streams.zip(tableHeaders.stream(),
                        tr.stream(), (th, td) -> new WebTableCell(th, td)).collect(Collectors.toList()))
                .collect(Collectors.toList());

    }

    public void   clickLauncher(String launcher)  {


            clickRetry(  driver.findElement(By.xpath("//a[contains(.,'"+launcher+"')]")));

    }
    public String downloadLauncher() throws InterruptedException {
        clickRetry(generate);
    clickRetry(download);
        Thread.sleep(5000);
    driver.navigate().to("chrome://downloads");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForElement.until((e)->js.executeScript("return document.readyState").toString().equals("complete"));

        Thread.sleep(5000);
      val downloadFileURL=   js.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#mainContainer > iron-list > downloads-item').shadowRoot.querySelector('#url').getAttribute(\"href\")").toString();

driver.navigate().to(downloadFileURL);
Thread.sleep(2000);
driver.navigate().refresh();
    return js.executeScript("return document.body.innerText").toString();
    }
}
