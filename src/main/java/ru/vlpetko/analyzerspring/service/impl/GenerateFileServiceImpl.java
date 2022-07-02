package ru.vlpetko.analyzerspring.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.service.GenerateFileService;

import java.io.IOException;
import java.util.List;

import static ru.vlpetko.analyzerspring.utils.FileUtils.createOfficeDocumentResource;

@Service
public class GenerateFileServiceImpl implements GenerateFileService {

    @Override
    public ByteArrayResource generateStocksInfoDocument(List<List<String>> stockLists, String fileName) {
        XSSFWorkbook workbook = convertToXLSXFile(stockLists, fileName);
        ByteArrayResource byteArrayResource = null;
        try {
            byteArrayResource = createOfficeDocumentResource(workbook, fileName, "XLSX");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayResource;
    }

    public XSSFWorkbook convertToXLSXFile(List<List<String>> stockLists, String fileName) {

        String[] names = new String[]{"Дата торгов", "Цена открытия", "Максимальная стоимость", "Минимальная стоимость",
                "Цена закрытия", "Уточненная цена", "Суммарный объем", "Наименование акции", "Номер отчета", "Дата загрузки"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("1");
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        BorderStyle borderStyle = BorderStyle.MEDIUM;
        style.setBorderBottom(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Row row = sheet.createRow(0);

        for (int i = 0; i < names.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(names[i]);
            cell.setCellStyle(style);
        }

        for (int i = 0; i < stockLists.size(); i++) {
            List<String> stockData = stockLists.get(i);
            row = sheet.createRow(i + 1);
            for (int j = 0; j < stockData.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(stockData.get(j));
                sheet.autoSizeColumn(j);
            }
        }
        return workbook;
    }
}
