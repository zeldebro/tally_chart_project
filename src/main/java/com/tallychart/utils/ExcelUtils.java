package com.tallychart.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        // 1. Create blank "Instructions" sheet
        workbook.createSheet("Instructions");

        // 2. Create "Questions" sheet
        Sheet sheet = workbook.createSheet("Questions");

        // 3. Header Row
        String[] headers = {
                "Sr. No", "Question Type", "Answer Type", "Topic Number", "Question (Text Only)",
                "Correct Answer 1", "Correct Answer 2", "Correct Answer 3", "Correct Answer 4", "Wrong Answer 1",
                "Wrong Answer 2", "Wrong Answer 3", "Time in seconds", "Difficulty Level",
                "Question (Image/ Audio/ Video)", "Contributor's Registered mailId", "Solution (Text Only)",
                "Solution (Image/ Audio/ Video)", "Variation Number"
        };

        Row headerRow = sheet.createRow(0);
        headerRow.setHeight((short) (14 * 20)); // Row height = 99

        CellStyle headerStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        headerStyle.setFont(boldFont);
        headerStyle.setAlignment(HorizontalAlignment.LEFT);
        headerStyle.setVerticalAlignment(VerticalAlignment.TOP);
        headerStyle.setWrapText(true);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Set column widths as per your spec
        for (int i = 0; i < headers.length; i++) {
            switch (i) {
                case 0: case 1: case 2: case 3:             // A–D (Meta fields)
                case 6: case 7: case 8:                     // G–I (Correct Ans 2–4)
                case 13: case 14: case 15:                  // N–P (DoD, etc.)
                    sheet.setColumnWidth(i, 2 * 256);
                    break;
                case 12: // M (Time)
                case 17: // R (Solution Media)
                case 18: // S (Variation Number)
                    sheet.setColumnWidth(i, 4 * 256);
                    break;
                case 4: // E (Question)
                    sheet.setColumnWidth(i, 40 * 256);
                    break;
                case 5: // F (Correct Answer)
                    sheet.setColumnWidth(i, 10 * 256);
                    break;
                case 9: case 10: case 11: // J–L (Wrong Answers)
                    sheet.setColumnWidth(i, 13 * 256);
                    break;
                case 16: // Q (Solution)
                    sheet.setColumnWidth(i, 40 * 256);
                    break;
                default:
                    sheet.setColumnWidth(i, 5 * 256); // Default for any overflow columns
                    break;
            }
        }

        // 4. Data Rows
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.TOP);
        dataStyle.setWrapText(true);

        int rowNum = 1;
        for (Question question : questions) {
            Row row = sheet.createRow(rowNum++);
            row.setHeight((short) (99 * 20)); // Row height = 99

            int col = 0;
            row.createCell(col++).setCellValue(question.getSrNo());
            row.createCell(col++).setCellValue(question.getQuestionType());
            row.createCell(col++).setCellValue(question.getAnswerType());
            row.createCell(col++).setCellValue(question.getTopicNo());
            row.createCell(col++).setCellValue(question.getQuestion());
            row.createCell(col++).setCellValue(question.getCorrectAnswer1());
            row.createCell(col++).setCellValue(question.getCorrectAnswer2());
            row.createCell(col++).setCellValue(question.getCorrectAnswer3());
            row.createCell(col++).setCellValue(question.getCorrectAnswer4());
            row.createCell(col++).setCellValue(question.getWrongAnswer1());
            row.createCell(col++).setCellValue(question.getWrongAnswer2());
            row.createCell(col++).setCellValue(question.getWrongAnswer3());
            row.createCell(col++).setCellValue(question.getTime());
            row.createCell(col++).setCellValue(question.getDod());
            row.createCell(col++).setCellValue(question.getQuestionMedia());
            row.createCell(col++).setCellValue(question.getContributorEmail());
            row.createCell(col++).setCellValue(question.getSolution());
            row.createCell(col++).setCellValue(question.getSolutionMedia());
            row.createCell(col++).setCellValue(question.getVariationNumber());

            for (int i = 0; i < headers.length; i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    cell.setCellStyle(dataStyle);
                }
            }
        }

        // 5. Set EOF marker at row 202 (index 201)
        Row eofRow = sheet.createRow(201);
        eofRow.setHeight((short) (99 * 20)); // Row height = 99
        eofRow.createCell(0).setCellValue("****");

        // 6. Write to file
        try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
            workbook.write(fileOut);
        }
    }
}
