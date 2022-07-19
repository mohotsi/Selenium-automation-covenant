package ae.proctected.covenant.Seleniumautomation.Page;

import ae.proctected.covenant.Seleniumautomation.model.WebTableCell;
import ae.proctected.covenant.Seleniumautomation.model.WebTableRow;
import ae.proctected.covenant.Seleniumautomation.annotation.Page;
import com.google.common.collect.Streams;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Page
public class CartPage extends CommonPage {
    @FindBy(id = "cart_summary")
    private WebElement shoppingCartSummary;

    @FindBy(className = "table")
    private WebElement table;


    public List<List<WebTableCell>> getShoppingCartSummary() {
        val table = waitUntilItIsDisplayed(shoppingCartSummary);
        val tableHeaders = table.findElements(By.tagName("th"));
        val tableRow = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        return tableRow.stream().map(tr -> tr.findElements(By.tagName("td")))
                .map(tr -> Streams.zip(tableHeaders.stream(),
                        tr.stream(), (th, td) -> new WebTableCell(th, td)).collect(Collectors.toList()))
                .collect(Collectors.toList());

    }

    public WebTableRow getProductRow(String productName) {
        return getShoppingCartSummary().stream().map(row -> new WebTableRow(row))
                .filter(row -> row.getColumnData("Description").findElement(By.tagName("a")).getText().equalsIgnoreCase(productName))
                .findFirst().orElseThrow(null);
    }
}
