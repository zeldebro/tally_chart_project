package com.tallychart.utils;

public class Question {

    private String srNo;
    private String questionType;
    private String answerType;
    private String topicNo;
    private String Question;
    private String correctAnswer1;
    private String correctAnswer2;
    private String correctAnswer3;
    private String correctAnswer4;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private String time;
    private String Dod;
    private String questionMedia;
    private String contributorEmail;
    private String solution;
    private String solutionMedia;
    private int variationNumber;

    public Question(String srNo, String questionType, String answerType, String topicNo,
                    String Question, String correctAnswer1, String correctAnswer2, String correctAnswer3,
                    String correctAnswer4, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3,
                    String time, String difficultyLevel, String questionMedia, String contributorEmail,
                    String solution, String solutionMedia, int variationNumber) {
        this.srNo = srNo;
        this.questionType = questionType;
        this.answerType = answerType;
        this.topicNo = topicNo;
        this.Question = Question;
        this.correctAnswer1 = correctAnswer1;
        this.correctAnswer2 = correctAnswer2;
        this.correctAnswer3 = correctAnswer3;
        this.correctAnswer4 = correctAnswer4;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.time = time;
        this.Dod = difficultyLevel;
        this.questionMedia = questionMedia;
        this.contributorEmail = contributorEmail;
        this.solution = solution;
        this.solutionMedia = solutionMedia;
        this.variationNumber = variationNumber;
    }

    // Getters
    public String getSrNo() { return srNo; }
    public String getQuestionType() { return questionType; }
    public String getAnswerType() { return answerType; }
    public String getTopicNo() { return topicNo; }
    public String getQuestion() { return Question; }
    public String getCorrectAnswer1() { return correctAnswer1; }
    public String getCorrectAnswer2() { return correctAnswer2; }
    public String getCorrectAnswer3() { return correctAnswer3; }
    public String getCorrectAnswer4() { return correctAnswer4; }
    public String getWrongAnswer1() { return wrongAnswer1; }
    public String getWrongAnswer2() { return wrongAnswer2; }
    public String getWrongAnswer3() { return wrongAnswer3; }
    public String getTime() { return time; }
    public String getDod() { return Dod; }
    public String getQuestionMedia() { return questionMedia; }
    public String getContributorEmail() { return contributorEmail; }
    public String getSolution() { return solution; }
    public String getSolutionMedia() { return solutionMedia; }
    public int getVariationNumber() { return variationNumber; }

    // Optional: for writing to Excel
    public String[] toArray() {
        return new String[] {
                srNo, questionType, answerType, topicNo, Question,
                correctAnswer1, correctAnswer2, correctAnswer3, correctAnswer4,
                wrongAnswer1, wrongAnswer2, wrongAnswer3,
                time, Dod, questionMedia,
                contributorEmail, solution, solutionMedia,
                String.valueOf(variationNumber)
        };
    }
}
