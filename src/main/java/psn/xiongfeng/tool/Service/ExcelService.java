package psn.xiongfeng.tool.Service;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelService {

    Workbook getWorkBook (String sheetName, String []title, String [][]values, Workbook wb, String fileName);
}
