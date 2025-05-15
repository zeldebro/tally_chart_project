package com.tallychart.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    public static void closeExcel() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM excel.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToExcel(List<Question> questions, String excelPath, Workbook workbook) throws IOException {
        Sheet sheet = workbook.createSheet("Tally Chart Options");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Question type", "Question", "Ans type", "Topic no.", "Correct option", "Wrong option", "Wrong option 2", "Wrong option 3", "Time DoD Question", "image", "Solution", "Solution (Image/Audio/Video)", "Variation Number"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate data rows
        int rowNum = 1;
        for (Question question : questions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(question.getQuestionType());
            row.createCell(1).setCellValue(question.getQuestion());
            row.createCell(2).setCellValue(question.getAnsType());
            row.createCell(3).setCellValue(question.getTopicNo());
            row.createCell(4).setCellValue(question.getCorrectOption());
            row.createCell(5).setCellValue(question.getWrongOption());
            row.createCell(6).setCellValue(question.getWrongOption2());
            row.createCell(7).setCellValue(question.getWrongOption3());
            row.createCell(8).setCellValue(question.getTimeDoDQuestion());
            row.createCell(9).setCellValue(question.getImage());
            row.createCell(10).setCellValue(question.getSolution());
            row.createCell(11).setCellValue(question.getSolutionImageAudioVideo());
            row.createCell(12).setCellValue(question.getVariationNumber());
        }

        // Adjust column width and apply text wrap and center alignment to all cells
        adjustExcelFormat(sheet);

        // Save the workbook
        try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
            workbook.write(fileOut);
        }
    }

    private static void adjustExcelFormat(Sheet sheet) {
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
            for (Row row : sheet) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    cellStyle.setWrapText(true);
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
    }
}
