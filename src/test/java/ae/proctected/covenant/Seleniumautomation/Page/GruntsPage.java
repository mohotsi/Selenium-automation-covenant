package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Page
public class GruntsPage extends CommonPage{


@FindBy(id = "interact-tab")
private WebElement interactTab;

//@FindBy(id = "grunt-name")
//private WebElement grantName;
//
@FindBy(xpath = "//input[contains(@placeholder,\"Interact\")]")
private WebElement interactInput;
@FindBy(xpath = "//button[contains(text(),'Send')]")
private WebElement send;
    public void clickTopGrunt() throws InterruptedException {
     Thread.sleep(2000);
    clickRetry(getTopGrunt());

    }
    public void clickInteractTab(){
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        interactTab.click();

    }
    public void sendACommand(String  command){
        interactInput.clear();
        interactInput.sendKeys(command);
        clickRetry(send);

    }
    public  WebElement getTopGrunt(){


       return getWebElementsTable().stream().map(row ->row.stream().filter(column->column.getTableHeader().getText().contains("Name")))
                .flatMap(row->row).collect(Collectors.toList()).
               stream().findFirst().get().getTableData().findElement(By.tagName("a"));
    }

}
