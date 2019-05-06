package psn.xiongfeng.tool.Service.ServiceImpl;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import psn.xiongfeng.tool.Service.ExcelService;
import psn.xiongfeng.tool.utils.ExcelUtils;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public Workbook getWorkBook(String sheetName, String[] title, String[][] values, Workbook wb, String fileName) {
        return ExcelUtils.getWorkbook(sheetName, title, values, wb, fileName);
    }
}
