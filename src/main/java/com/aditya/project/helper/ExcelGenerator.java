package com.aditya.project.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ExcelGenerator {

    public OutputStream generateExcelFile(List<Object[]> data, List<String> columns, String sheetName,
                                          boolean addHeader, OutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);
            int rowCount = 0;
            if (addHeader) {
                createHeaderRow(sheet, columns);
                rowCount++;
            }
            for (Object[] rowData : data) {
                createDataRow(sheet, rowData, rowCount++);
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return outputStream;
    }

    private void createHeaderRow(Sheet sheet, List<String> columns) {
        Row row = sheet.createRow(0);
        int columnCount = 0;

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        for (String column : columns) {
            sheet.setColumnWidth(columnCount, (column.length() + 5) * 256);
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(column);
            cell.setCellStyle(cellStyle);
        }
    }

    private void createDataRow(Sheet sheet, Object[] rowData, int rowCount) {
        Row row = sheet.createRow(rowCount);
        int columnCount = 0;

        for (Object data : rowData) {
            Cell cell = row.createCell(columnCount++);
            if (data instanceof String) cell.setCellValue((String) data);
            if (data instanceof Integer) cell.setCellValue((Integer) data);
            if (data instanceof Long) cell.setCellValue((Long) data);
            if (data instanceof Date) cell.setCellValue((Date) data);
            if (data instanceof Boolean) cell.setCellValue((Boolean) data);
        }
    }
}
