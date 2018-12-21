package org.unicome.cms.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractExcelParser implements ExcelParserInterface {
    public static final String EXCEL_SUFFIX_XLS = "xls";
    public static final String EXCEL_SUFFIX_XLSX = "xlsx";

    @Override
    public Map<String, String> parse(File file) throws ExcelException {
        Workbook workbook = null;
        try {
            workbook = getWorkbook(file);
            Map<String, Object> data = this.parseSheets(workbook);
            Map<String, String> result = this.handleData(data);
            return result;
        } catch (Exception e) {
            log.error("Parse excel error!", e);
            throw new ExcelException(e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("Close excel error!", e);
                }
            }
        }
    }

    @Override
    public Map<String, String> parse2(File file) throws ExcelException {
        return null;
    }

    private Workbook getWorkbook(File file) throws IOException {
        Workbook workbook = null;
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        switch (suffix) {
            case AbstractExcelParser.EXCEL_SUFFIX_XLS:
                workbook = new HSSFWorkbook(new FileInputStream(file));
                break;
            case AbstractExcelParser.EXCEL_SUFFIX_XLSX:
                workbook = new XSSFWorkbook(new FileInputStream(file));
                break;
            default:
                throw new IOException("The file suffix must be in [" + AbstractExcelParser.EXCEL_SUFFIX_XLS + ", " + AbstractExcelParser.EXCEL_SUFFIX_XLSX + "]");
        }
        return workbook;
    }

    public Map<String, Object> parseSheets (Workbook workbook) throws ExcelException {
        int number = workbook.getNumberOfSheets();
        if (number < 1) {
            log.error("No excel sheets.");
            throw new ExcelException("No excel sheets");
        }
        Map<String, Object> data = new HashMap<String, Object>();
        for (int index = 0; index < number; index++) {
            Sheet sheet = workbook.getSheetAt(index);
            // TODO
        }
        return data;
    }

    public Map<String, String> handleData(Map<String, Object> data) throws Exception {
        // TODO
        return null;
    }
}