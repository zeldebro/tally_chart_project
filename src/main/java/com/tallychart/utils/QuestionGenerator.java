package com.tallychart.utils;
import java.util.stream.Collectors;
import java.util.*;
public class QuestionGenerator {

    private static final String[] ITEM_IMAGES = {
            "0901_110_biscuit.png",
            "0901_110_cadbury.png",
            "0901_110_chocolate.png",
            "0901_110_coconut toffee.png",
            "0901_110_cream biscuit.png",
            "0901_110_cupcake.png",
            "0901_110_donut.png",
            "0901_110_fruit jelly canday.png",
            "0901_110_icecrem.png",
            "0901_110_lolipop.png",
            "0901_110_pestry.png",
            "0901_110_strawberry toffee.png"
    };

    private static final String[] ITEM_NAMES_ENGLISH = {
            "Biscuit",
            "Cadbury",
            "Chocolate",
            "Coconut Toffee",
            "Cream Biscuit",
            "Cupcake",
            "Donut",
            "Fruit Jelly Candy",
            "Ice Cream",
            "Lollipop",
            "Pastry",
            "Strawberry Toffee"    };

    private static final String[] ITEM_NAMES_MARATHI = {
            "बिस्किट",
            "कॅडबरी",
            "चॉकलेट",
            "नारळ टॉफी",
            "क्रीम बिस्किट",
            "कप केक",
            "डोनट",
            "फ्रुट जेली टॉफी",
            "आईस्क्रीम",
            "लॉलीपॉप",
            "पेस्ट्री",
            "स्ट्रॉबेरी टॉफी"
    };



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
            uniqueIndices.add(rand.nextInt(ITEM_IMAGES.length));
        }
        Integer[] indices = uniqueIndices.toArray(new Integer[0]);

        int[] counts = new int[3];
        for (int i = 0; i < 3; i++) {
            counts[i] = rand.nextInt(4) + 2;
        }

        StringBuilder questionHTML = new StringBuilder();
        questionHTML.append("Observe the following table of items. Which tally chart among the options is correct?<br>")
                .append("खाली दिलेल्या चित्रावरून ताळ्यांच्या खुणांचा तक्ता तयार केला असता, पुढील पैकी कोणता तक्ता बरोबर आहे?<br>");

        questionHTML.append("<table border='2' style='border-collapse: collapse; text-align: center; width: 100%;'>")
                .append("<tr><th>Item Name/<br>वस्तूचे नाव</th><th>Items/<br>वस्तूंची संख्या</th></tr>");
        for (int i = 0; i < 3; i++) {
            questionHTML.append("<tr border='2' style='height: 70px;'><td>")
                    .append(ITEM_NAMES_ENGLISH[indices[i]]).append(" /<br> ").append(ITEM_NAMES_MARATHI[indices[i]]).append("</td><td>");
            for (int j = 0; j < counts[i]; j++) {
                questionHTML.append("<img src='https://portal.coepvlab.ac.in/VirtualMathsLab/media/")
                        .append(ITEM_IMAGES[indices[i]].replace(" ", "%20"))
                        .append("' style='width: 50px; height: 70px; margin:3px;float: left;'/>");
            }
            questionHTML.append("</td></tr>");
        }
        questionHTML.append("</table><br><br>");

        String correctOptionHTML = buildTallyTable(indices, counts);
        List<String> labels = Arrays.asList("Option / पर्याय A", "Option / पर्याय B", "Option / पर्याय C", "Option / पर्याय D");
        List<String> options = new ArrayList<>();
        int correctIndex = rand.nextInt(4) ;
        for (int i = 0; i < 4; i++) {
            if (i == correctIndex) {
                options.add("<b>" + labels.get(i) + "</b><br>" + correctOptionHTML);
            } else {
                options.add("<b>" + labels.get(i) + "</b><br>" + buildWrongOptionTable(indices, counts));
            }
        }

        return new Question(
                srNo, "Text", "1", "03", questionHTML.toString(),
                options.get(correctIndex), "", "", "",
                options.get(getWrongIndex(0, correctIndex)),
                options.get(getWrongIndex(1, correctIndex)),
                options.get(getWrongIndex(2, correctIndex)),
                "150", "3", "", "sanjivanibabalsure@gmail.com",
                generateSolutionExplanation(indices, counts, labels.get(correctIndex)), "", 110
        );
    }

    private static int getWrongIndex(int i, int correctIndex) {
        List<Integer> indices = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        indices.remove(Integer.valueOf(correctIndex));
        return indices.get(i);
    }

    private static String buildTallyTable(Integer[] indices, int[] counts) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='2' style='border-collapse: collapse; text-align: center; width:100%;'>")
                .append("<tr><th>Item Name/<br>वस्तूचे नाव</th><th>Items/<br>वस्तूंची संख्या</th><th>Tally Marks/<br>ताळ्यांच्या खुणा</th></tr>");
        for (int i = 0; i < 3; i++) {
            sb.append("<tr border='2'style='height: 70px;'><td>")
                    .append(ITEM_NAMES_ENGLISH[indices[i]]).append(" /<br> ").append(ITEM_NAMES_MARATHI[indices[i]])
                    .append("</td><td><div style='white-space: nowrap;'>");

            for (int j = 0; j < counts[i]; j++) {
                sb.append("<img src='https://portal.coepvlab.ac.in/VirtualMathsLab/media/")
                        .append(ITEM_IMAGES[indices[i]].replace(" ", "%20"))
                        .append("' style='width: 50px; height: 70px; margin:3px; float: left;' />");
            }
            sb.append("</div></td><td>").append(formatTally(counts[i])).append("</td></tr>");
        }
        sb.append("</table><br>");
        return sb.toString();
    }

    private static String formatTally(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("<span style='font-size:25px; font-weight:10px; gap:2px;'>");
        for (int i = 1; i <= count; i++) {
            sb.append("|");
            if (i % 5 == 0 && i != count) sb.append("|");
        }
        sb.append("</span>");
        return sb.toString();
    }

    private static String buildWrongOptionTable(Integer[] correctIndices, int[] correctCounts) {
        Set<Integer> usedIndices = new HashSet<>(Arrays.asList(correctIndices));
        List<Integer> selectedIndices = new ArrayList<>();

        while (selectedIndices.size() < 3) {
            int idx = rand.nextInt(ITEM_IMAGES.length);
            if (!usedIndices.contains(idx) && !selectedIndices.contains(idx)) {
                selectedIndices.add(idx);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<table border='2' style='border-collapse: collapse; text-align: center; width:100%;'>")
                .append("<tr><th>Item Name/<br>वस्तूचे नाव</th><th>Items/<br>वस्तूंची संख्या</th><th>Tally Marks/<br>ताळ्यांच्या खुणा</th></tr>");

        for (int i = 0; i < 3; i++) {
            int count = rand.nextInt(4) + 2;
            int wrongTally = getMismatchTally(count);
            sb.append("<tr style='height: 70px;'><td>")
                    .append(ITEM_NAMES_ENGLISH[selectedIndices.get(i)]).append(" /<br> ")
                    .append(ITEM_NAMES_MARATHI[selectedIndices.get(i)])
                    .append("</td><td><div style='white-space: nowrap;'>");
            for (int j = 0; j < count; j++) {
                sb.append("<img src='https://portal.coepvlab.ac.in/VirtualMathsLab/media/")
                        .append(ITEM_IMAGES[selectedIndices.get(i)].replace(" ", "%20"))
                        .append("' style='width: 50px; height: 70px; margin:3px;float: left;' />");
            }
            sb.append("</div></td><td>").append(formatTally(wrongTally)).append("</td></tr>");
        }
        sb.append("</table><br>");
        return sb.toString();
    }

    private static int getMismatchTally(int correctCount) {
        List<Integer> options = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if (i != correctCount) options.add(i);
        }
        return options.get(rand.nextInt(options.size()));
    }



    private static List<Integer> getUniqueIndices(int count) {
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < count) {
            set.add(rand.nextInt(ITEM_IMAGES.length));
        }
        return new ArrayList<>(set);
    }

    private static String generateSolutionExplanation(Integer[] indices, int[] counts, String optionLabel) {
        String optionLetter = optionLabel.substring(optionLabel.length() - 1); // Extract "A", "B", etc.
        String englishLabel = "Option " + optionLetter;
        String marathiLabel = "पर्याय " + optionLetter;
        String tallyChartLabel = "" + optionLetter;

        StringBuilder sb = new StringBuilder();


        // Tally chart
        sb.append("<b>Option:</b><br>")
                .append("Answer : <b>").append(englishLabel).append(".</b><br><br>")
                .append(buildTallyTable(indices, counts)).append("<br><br><br>");


        // English Explanation
        sb.append("Ans : <b>").append(englishLabel).append(".</b><br>");
        sb.append("The objects shown in the picture are ")
                .append(ITEM_NAMES_ENGLISH[indices[0]]).append(", ")
                .append(ITEM_NAMES_ENGLISH[indices[1]]).append(", and ")
                .append(ITEM_NAMES_ENGLISH[indices[2]]).append(".<br>");
        sb.append("We will can make one tally mark for each item.<br>");
        sb.append("In the first column, we will show names of the the items.<br>");
        sb.append("In the second column, we will show pictures of the the items.<br>");
        sb.append("In the third column, we will place tally marks corresponding to each item.<br>");
        for (int i = 0; i < 3; i++) {
            sb.append("There are $").append(counts[i]).append("$ ")
                    .append(ITEM_NAMES_ENGLISH[indices[i]])
                    .append(", so we show ").append(counts[i])
                    .append(" items and place ").append(counts[i]).append(" tally marks in that row.<br>");
        }
        sb.append("Now, we compare this table with all the option tables one by one.<br>");
        sb.append("Tally marks as well as objects in this table match only with the table in option $")
                .append(tallyChartLabel).append("$.<br></b>");
        sb.append("In all other tables, either the tally marks don't match or the objects don't match.<br>");
        sb.append("Hence, option $").append(tallyChartLabel).append("$ is the answer.<br>");

        // Marathi Answer header
        sb.append("उत्तर : <b>") .append(marathiLabel).append(".</b><br>");
        sb.append("दिलेल्या चित्रात दाखवलेल्या वस्तू ")
                .append(ITEM_NAMES_MARATHI[indices[0]]).append(", ")
                .append(ITEM_NAMES_MARATHI[indices[1]]).append(", आणि ")
                .append(ITEM_NAMES_MARATHI[indices[2]]).append(" आहेत.<br>");
        sb.append("प्रत्येक वस्तूसाठी आपण एक ताळ्याची खुण करायची आहेे.<br>");
        sb.append("पहिल्या रकान्यात वस्तूचे नाव लिहू.<br>");
        sb.append("दुसऱ्या रकान्यात वस्तू दाखवायच्या आहेत.<br>");
        sb.append("त्या ओळीतील तिसऱ्या रकान्यात प्रत्येक वस्तू साठी एक ताळ्याची खुण करू.<br>");
        sb.append("पहिल्या ओळीत $").append(counts[0]).append("$ ").append(ITEM_NAMES_MARATHI[indices[0]])
                .append(" आहेत, म्हणून या ओळीत $").append(counts[0]).append("$ ताळ्याच्या खुणा करू.<br>");
        sb.append("दुसऱ्या ओळीत ").append(ITEM_NAMES_MARATHI[indices[1]])
                .append(" साठी, $").append(counts[1]).append("$ ताळ्याच्या खुणा करू.<br>");
        sb.append("तिसऱ्या ओळीत ").append(ITEM_NAMES_MARATHI[indices[2]])
                .append("आहेत, म्हणून या ओळीत $").append(counts[2]).append("$ ताळ्याच्या खुणा करू.<br>");
        sb.append("आता तयार झालेल्या तक्त्याची दिलेल्या तक्त्यांशी त्यातील वस्तू आणि ताळ्यांच्या खुणा यासाठी तुलना करू.<br>");
        sb.append("अशी तुलना केली असता फक्त पर्याय $").append(tallyChartLabel).append("$ मधील ताळ्याच्या तक्ता आपण तयार केलेल्या तक्त्याशी जुळतो.<br>");
        sb.append("कारण त्यातील वस्तू आणि त्या साठीच्या ताळ्याच्या खुणा दिलेल्या माहितीशी तंतोतंत जुळतात.<br>");
        sb.append("इतर सर्व पर्यायांमध्ये काही ठिकाणी ताळ्याच्या खुणा जुळत नाहीत, तर काही ठिकाणी वस्तू वेगळ्या आहेत.<br>");
        sb.append("म्हणून, पर्याय $").append(tallyChartLabel).append("$ हे योग्य उत्तर आहे.<br>");

        return sb.toString();
    }
}


