package com.tallychart;

import com.tallychart.utils.ExcelUtils;
import com.tallychart.utils.QuestionGenerator;
import com.tallychart.utils.Question;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Close Excel if it's open
        ExcelUtils.closeExcel();

        // Create the report folder if it doesn't exist
        File reportFolder = new File("src/resources/report");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        // Ask the user how many questions they want to generate
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many questions do you want to generate? (up to 1000) ");
        int numQuestions = scanner.nextInt();

        // Generate the specified number of questions (up to 1000)
        List<Question> questions = QuestionGenerator.generateQuestions(numQuestions);

        // Export the DataFrame to an Excel file in the report folder
        String excelPath = "src/resources/report/tally_chart_options.xlsx";
        try (Workbook workbook = new XSSFWorkbook()) {
            ExcelUtils.saveToExcel(questions, excelPath, workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The tally chart options have been successfully exported to " + excelPath);
    }
}
