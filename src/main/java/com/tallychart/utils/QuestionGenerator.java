package com.tallychart.utils;

import java.util.*;

public class QuestionGenerator {

    private static final String[] FLOWER_IMAGES = {
            "Rose.png", "Lotus2.png", "Sunflower.png", "Marigold.png", "Jasmine.png",
            "Hibiscus.png", "Lily1.png", "Periwinkle.png", "Gulmohar.png", "Champa.png", "Lavender.png"
    };

    private static final String[] FLOWER_NAMES_ENGLISH = {
            "Rose", "Lotus", "Sunflower", "Marigold", "Jasmine",
            "Hibiscus", "Lily", "Periwinkle", "Gulmohar", "Champa", "Lavender"
    };

    private static final String[] FLOWER_NAMES_MARATHI = {
            "गुलाब", "कमळ", "सूर्यफूल", "झेंडू", "मोगरा",
            "जास्वंद", "कमळजाई", "सदाफुली", "गुलमोहर", "चंपा", "लव्हेंडर"
    };

    private static final String[] OPTION_LABELS = {"A", "B", "C", "D"};
    private static final Random rand = new Random();

    public static List<Question> generateQuestions(int count) {
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            questions.add(generateQuestion(String.valueOf(i)));
        }
        return questions;
    }

    private static Question generateQuestion(String srNo) {
        Set<Integer> uniqueIndices = new LinkedHashSet<>();
        while (uniqueIndices.size() < 3) {
            uniqueIndices.add(rand.nextInt(FLOWER_IMAGES.length));
        }
        Integer[] indices = uniqueIndices.toArray(new Integer[0]);

        int[] counts = new int[3];
        for (int i = 0; i < 3; i++) {
            counts[i] = rand.nextInt(4) + 2; // 2 to 5
        }

        String correctOption = buildImageOption(indices, counts, true);
        List<String> wrongOptions = generateUniqueWrongOptions(correctOption, indices, counts);

        List<String> options = new ArrayList<>(wrongOptions);
        options.add(correctOption);
        Collections.shuffle(options);

        int correctIndex = options.indexOf(correctOption);
        String correctLabel = OPTION_LABELS[correctIndex];

        String questionText = "Observe the tally chart below showing the number of different flowers using images.<br>" +
                "Which option shows the same number of flowers and tally marks?<br>#खाली फुलांची संख्या आणि टॅली मार्क दाखवणारा चार्ट पाहा. " +
                "खालील पर्यायांपैकी कोणता फुलांची संख्या आणि टॅली मार्क योग्य दाखवतो?<br>";

        return new Question(
                srNo,
                "Text",
                "1",
                "03",
                questionText,
                options.get(correctIndex),
                "",
                "",
                "",
                options.get(getWrongIndex(0, correctIndex)),
                options.get(getWrongIndex(1, correctIndex)),
                options.get(getWrongIndex(2, correctIndex)),
                "150",
                "3",
                "",
                "sanjivanibabalsure@gmail.com",
                generateSolutionExplanation(indices, counts, correctLabel, options),
                "",
                110
        );
    }

    private static String buildImageOption(Integer[] indices, int[] counts, boolean shuffle) {
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) order.add(i);
        if (shuffle) Collections.shuffle(order);

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1' style='border-collapse: collapse; text-align: center; width:100%;'>")
                .append("<tr><th style='font-weight:bold; text-align:center; white-space: normal;'>Flower</th><th style='font-weight:bold; text-align:center; white-space: normal;'>Tally Marks</th></tr>");

        for (int i : order) {
            sb.append("<tr style='height: 60px;'><td style='text-align:center;'>");
            for (int j = 0; j < counts[i]; j++) {
                sb.append("<img src='tally_chart_project/imges/")  // KEEPING 'imges' as you asked
                        .append(FLOWER_IMAGES[indices[i]])
                        .append("' width='30' height='30' style='margin:2px;' />");
            }
            sb.append("</td><td style='text-align:center;'>");
            for (int k = 0; k < counts[i]; k++) {
                sb.append("|");
            }
            sb.append("</td></tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }

    private static List<String> generateUniqueWrongOptions(String correctOption, Integer[] indices, int[] counts) {
        Set<String> uniqueOptions = new HashSet<>();
        uniqueOptions.add(correctOption);

        List<String> wrongOptions = new ArrayList<>();
        int tries = 0;

        while (wrongOptions.size() < 3 && tries < 50) {
            int[] modifiedCounts = new int[counts.length];
            for (int i = 0; i < counts.length; i++) {
                int flowerCount = counts[i];
                int wrongTally = flowerCount;
                while (wrongTally == flowerCount) {
                    int change = rand.nextBoolean() ? 1 : -1;
                    wrongTally = flowerCount + change;
                    if (wrongTally < 1) wrongTally = flowerCount + 1;
                }
                modifiedCounts[i] = wrongTally;
            }

            String option = buildImageOption(indices, modifiedCounts, true);

            if (uniqueOptions.add(option)) {
                wrongOptions.add(option);
            }

            tries++;
        }

        return wrongOptions;
    }

    private static int getWrongIndex(int skip, int correctIndex) {
        int found = 0;
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                if (found == skip) return i;
                found++;
            }
        }
        return -1;
    }

    private static String generateSolutionExplanation(Integer[] indices, int[] counts, String correctLabel, List<String> options) {
        StringBuilder sb = new StringBuilder();

        sb.append("Ans: ").append(correctLabel).append("<br>");
        sb.append("The table shows flowers and their corresponding tally marks.<br>#")
                .append("सदर तक्ता फुलांची संख्या आणि त्यांच्या टॅली मार्क दर्शवतो.<br><br>");

        sb.append("<b>Correct Option:</b><br>");
        sb.append("<b>Option ").append(correctLabel).append(" (Correct)</b><br>");
        sb.append(options.get(OPTION_LABELS_TO_INDEX(correctLabel))).append("<br><br>");

        sb.append("<b>Other Options:</b><br>");
        for (int i = 0; i < 4; i++) {
            String label = OPTION_LABELS[i];
            if (!label.equals(correctLabel)) {
                sb.append("<b>Option ").append(label).append(" (Wrong)</b><br>");
                sb.append(options.get(i)).append("<br><br>");
            }
        }

        sb.append("Now, we will compare this table with all the option tables, one by one.<br>");
        sb.append("Tally marks in this table match only with table in option ").append(correctLabel).append(".<br>");
        sb.append("In all other tables, either the tally marks don't match or the images don't match.<br>");
        sb.append("Hence, option ").append(correctLabel).append(" is the answer.<br>#");

        sb.append("उत्तर: ").append(correctLabel).append(".<br>");
        sb.append("तक्त्यामधील वस्तू आणि त्यांच्या टॅली खुणा फक्त पर्याय ").append(correctLabel).append(" मध्ये बरोबर आहेत.<br>");
        sb.append("म्हणून, पर्याय ").append(correctLabel).append(" हे योग्य उत्तर आहे.<br>");

        return sb.toString();
    }

    private static int OPTION_LABELS_TO_INDEX(String label) {
        for (int i = 0; i < OPTION_LABELS.length; i++) {
            if (OPTION_LABELS[i].equals(label)) {
                return i;
            }
        }
        return -1;
    }
}
