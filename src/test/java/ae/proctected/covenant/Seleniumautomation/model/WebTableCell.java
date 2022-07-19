package ae.proctected.covenant.Seleniumautomation.model;

import org.openqa.selenium.WebElement;

public class WebTableCell {

    private WebElement TableHeader;
    private WebElement TableData;

    public WebTableCell(WebElement tableHeader, WebElement tableData) {
        TableHeader = tableHeader;
        TableData = tableData;
    }

    public WebElement getTableHeader() {
        return TableHeader;
    }

    public WebElement getTableData() {
        return TableData;
    }


}
