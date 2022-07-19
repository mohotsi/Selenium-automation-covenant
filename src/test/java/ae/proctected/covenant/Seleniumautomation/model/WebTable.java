package ae.proctected.covenant.Seleniumautomation.model;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WebTable {
   private List<List<WebTableCell>>  webTableCells;

    public WebTable(List<List<WebTableCell>> webTableCells) {
        this.webTableCells = webTableCells;
    }
    public  List<WebElement> getWebCellsForHeaderName(String header){
       return  webTableCells.stream().map(row->row.stream().filter(column->column.getTableData().getText().equalsIgnoreCase(header)))
               .findFirst().get().map(row->row.getTableData()).collect(Collectors.toList());
    }

}
