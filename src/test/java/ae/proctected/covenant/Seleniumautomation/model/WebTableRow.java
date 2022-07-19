package ae.proctected.covenant.Seleniumautomation.model;

import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTableRow {
 List<WebTableCell> row;

    public WebTableRow(List<WebTableCell> webTableCells) {
        this.row = webTableCells;
    }
    public WebElement getColumnData(String header){
      return  row.stream().filter(cell->cell.getTableHeader().getText().contains(header)).
              findFirst().get().getTableData();
    }
}
