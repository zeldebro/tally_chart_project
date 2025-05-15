package com.tallychart.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private static final String[] FLOWER_EMOJIS = {"🌹", "🌺", "🌼", "🌷", "🌻", "🌸", "🌵", "🌿", "🌾", "🌱", "🍇"};

    public static List<Question> generateQuestions(int numQuestions) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < Math.min(numQuestions, 1000); i++) {
            questions.add(generateQuestion());
        }
        return questions;
    }

    private static Question generateQuestion() {
        Random random = new Random();
        String[] flowers = new String[4];
        int[] counts = new int[4];
        for (int i = 0; i < 4; i++) {
            flowers[i] = FLOWER_EMOJIS[random.nextInt(FLOWER_EMOJIS.length)];
            counts[i] = random.nextInt(10) + 1;
        }

        String correctOption = "\\[\\begin{array}{|c|c|}\\hline Flower & Tally Marks \\\\ \\hline ";
        String wrongOption = "\\[\\begin{array}{|c|c|}\\hline Flower & Tally Marks \\\\ \\hline ";
        String wrongOption2 = "\\[\\begin{array}{|c|c|}\\hline Flower & Tally Marks \\\\ \\hline ";
        String wrongOption3 = "\\[\\begin{array}{|c|c|}\\hline Flower & Tally Marks \\\\ \\hline ";

        for (int i = 0; i < 4; i++) {
            correctOption += flowers[i].repeat(counts[i]) + " & " + "|".repeat(counts[i]) + " \\\\ \\hline ";
            wrongOption += flowers[i].repeat(counts[i]) + " & " + "|".repeat(counts[i] - 1) + " \\\\ \\hline ";
            wrongOption2 += flowers[i].repeat(counts[i] + 1) + " & " + "|".repeat(counts[i]) + " \\\\ \\hline ";
            wrongOption3 += flowers[i].repeat(counts[i]) + " & " + "|".repeat(counts[i] + 1) + " \\\\ \\hline ";
        }

        correctOption += "\\end{array}\\]";
        wrongOption += "\\end{array}\\]";
        wrongOption2 += "\\end{array}\\]";
        wrongOption3 += "\\end{array}\\]";

        return new Question(
                "Text",
                "From the image shown here, a tally mark chart is to be prepared. Which of the chart given in the options, will be a correct tally chart for this?",
                "Single Correct",
                1,
                correctOption,
                wrongOption,
                wrongOption2,
                wrongOption3,
                "",
                "",
                "\\textbf{Explanation:} The correct tally chart should accurately represent the count of each type of flower using tally marks.",
                "",
                1
        );
    }
}
