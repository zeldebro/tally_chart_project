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
        // Ensure Excel is not open before writing
        ExcelUtils.closeExcel();

        // Create the report folder if it doesn't exist
        File reportFolder = new File("src/resources/report");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        // Ask the user for the number of questions to generate
        Scanner scanner = new Scanner(System.in);
        int numQuestions = 0;

        while (numQuestions <= 0 || numQuestions > 1000) {
            System.out.print("How many questions do you want to generate? (1 to 1000): ");
            if (scanner.hasNextInt()) {
                numQuestions = scanner.nextInt();
            } else {
                scanner.next(); // Clear invalid input
            }
        }

        // Generate the questions
        List<Question> questions = QuestionGenerator.generateQuestions(numQuestions);

        // Define the output Excel path
        String excelPath = "src/resources/report/Intern_0901_110_Asign1_Sanjivani.xlsx";

        // Save the questions to Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            ExcelUtils.saveToExcel(questions, excelPath, workbook);
            System.out.println("The tally chart questions have been successfully exported to: " + excelPath);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        }
    }
}
