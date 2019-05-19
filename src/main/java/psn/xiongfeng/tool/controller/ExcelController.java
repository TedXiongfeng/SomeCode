package psn.xiongfeng.tool.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psn.xiongfeng.tool.Service.ExcelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping(value = "/excelExport")
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        String[][] list = {{"史塔克", "男", "三"}, {"罗杰斯", "男", "六"}, {"索尔", "男", "六"}, {"娜塔莎", "女", "四"}, {"旺达", "女", "二"}};

        //excel标题
        String[] title = {"姓名", "性别", "年级"};
        //excel文件名
        String fileName = "学生信息表.xlsx";
        //sheet名
        String sheetName = "学生信息表";

        //创建Workbook
        Workbook wb = excelService.getWorkBook(sheetName, title, list, null, fileName);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
