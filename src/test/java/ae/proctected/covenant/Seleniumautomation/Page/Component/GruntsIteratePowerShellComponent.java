package ae.proctected.covenant.Seleniumautomation.Page.Component;

import ae.proctected.covenant.Seleniumautomation.Page.CommonPage;
import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Page
public class GruntsIteratePowerShellComponent extends CommonPage {


@FindBy(id = "")
private WebElement interactTab;

//@FindBy(id = "grunt-name")
//private WebElement grantName;
//
@FindBy(xpath = "//*[contains(@id,'tasking-card')]")
private List<WebElement> taskingCard;


    /**
     * driver.findElements(By.xpath("//*[contains(@id,'tasking-card')]"))
     *
     *
     *
     * driver.findElements(By.xpath("//*[contains(@id,'tasking-card')]")).stream().map(taskCard->taskCard.findElement(By.xpath("//*[contains(@class,'task-text-head')]")).getText()).collect(Collectors.toList())
     *
     *
     * driver.findElements(By.xpath("//*[contains(@id,'tasking-card')]"))
     *
     */
    public void clickTopGrunt() throws InterruptedException {

     Thread.sleep(2000);
    clickRetry(getTopGrunt());

    }
    public Boolean taskAreCompleted(){

       return taskingCard.stream().map(taskCard->taskCard.findElement(By.xpath("//*[contains(@class,'task-text-head')]")).getText()
                .contains("complete")).collect(Collectors.toSet()).equals(Set.of(true));


    }
    public List<String> getTaskResponses(){
        waitForElement.until((e)->taskAreCompleted());
        return   taskingCard.stream()
                .map(taskCard->taskCard.findElement(By.xpath("//*[contains(@class,'card-body')]"))
                        .getText().trim()).collect(Collectors.toList());
    }
    public List<String> getTaskCommand(){
        waitForElement.until((e)->taskAreCompleted());
      return   taskingCard.stream()
              .map(taskCard->taskCard.findElement(By.xpath("//*[contains(@class,'task-text-body')]"))
                      .getText().replaceAll(".*?>(.+)","$1").trim()).collect(Collectors.toList());
    }

    public  WebElement getTopGrunt(){


       return getWebElementsTable().stream().map(row ->row.stream().filter(column->column.getTableHeader().getText().contains("Name")))
                .flatMap(row->row).collect(Collectors.toList()).
               stream().findFirst().get().getTableData().findElement(By.tagName("a"));
    }

}
