package org.unicome.cms.helper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.util.CollectionUtils;
import java.util.List;

interface ExcelExporterCallback<T> {
    void callback(HSSFSheet sheet, List<T> list);
}

/**
 * 泛型 - 导出excel
 * @param <T>
 */
public class ExcelExporter<T> {
    public HSSFWorkbook execute(String sheetName, String[] titles, List<T> list, ExcelExporterCallback excelExporterCallback) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow header = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
//        设置标题
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = header.createCell((i));
            HSSFFont font = wb.createFont();
            font.setBold(true);
            HSSFCellStyle _style = wb.createCellStyle();
            _style.setFont(font);
            cell.setCellStyle(_style);
            cell.setCellValue(titles[i]);
        }
        excelExporterCallback.callback(sheet, list);
        return wb;
    }

//    EXCEMPLE
    public static HSSFWorkbook exportExcel(List<String> list) {
        String[] titles = {"第一列", "第二列"};
        String sheetName = "";
        return new ExcelExporter<String>().execute(sheetName, titles, list, new ExcelExporterCallback<String>() {
            @Override
            public void callback(HSSFSheet sheet, List<String> list) {
                if (!CollectionUtils.isEmpty(list)) {
                    for (int j = 1; j <= list.size(); j++) {
                        HSSFRow row = sheet.createRow((j));
                        HSSFCell cell = row.createCell((0));
                        cell.setCellValue(list.get(j - 1));
                        cell = row.createCell((1));
                        cell.setCellValue(list.get(j - 1));
                    }
                }
            }
        });
    }
}
