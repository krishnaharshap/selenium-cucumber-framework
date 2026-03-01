package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private static final Logger logger = LogManager.getLogger(ExcelReader.class);
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            logger.info("Excel file loaded successfully: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to load Excel file: {}", e.getMessage());
            throw new RuntimeException("Excel file not found: " + filePath);
        }
    }

    public ExcelReader() {
        this(FrameworkConstants.TEST_DATA_PATH);
    }

    public void setSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            logger.error("Sheet not found: {}", sheetName);
            throw new RuntimeException("Sheet not found: " + sheetName);
        }
        logger.info("Sheet set to: {}", sheetName);
    }

    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    public int getColumnCount() {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            logger.error("Header row is empty in sheet: {}", sheet.getSheetName());
            throw new RuntimeException("Header row not found in sheet: " + sheet.getSheetName());
        }
        return headerRow.getLastCellNum();
    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return getCellValue(cell);
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {
        setSheet(sheetName);
        return getCellData(rowNum, colNum);
    }

    public Map<String, String> getRowData(int rowNum) {
        Map<String, String> data = new HashMap<>();
        Row headerRow = sheet.getRow(0);
        Row dataRow = sheet.getRow(rowNum);

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String header = getCellValue(headerRow.getCell(i));
            String value = getCellValue(dataRow.getCell(i));
            data.put(header, value);
        }
        return data;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
                logger.info("Excel workbook closed");
            }
        } catch (IOException e) {
            logger.error("Failed to close workbook: {}", e.getMessage());
        }
    }
}
