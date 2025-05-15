package com.tallychart.utils;

public class Question {
    private String questionType;
    private String question;
    private String ansType;
    private int topicNo;
    private String correctOption;
    private String wrongOption;
    private String wrongOption2;
    private String wrongOption3;
    private String timeDoDQuestion;
    private String image;
    private String solution;
    private String solutionImageAudioVideo;
    private int variationNumber;

    public Question(String questionType, String question, String ansType, int topicNo, String correctOption, String wrongOption, String wrongOption2, String wrongOption3, String timeDoDQuestion, String image, String solution, String solutionImageAudioVideo, int variationNumber) {
        this.questionType = questionType;
        this.question = question;
        this.ansType = ansType;
        this.topicNo = topicNo;
        this.correctOption = correctOption;
        this.wrongOption = wrongOption;
        this.wrongOption2 = wrongOption2;
        this.wrongOption3 = wrongOption3;
        this.timeDoDQuestion = timeDoDQuestion;
        this.image = image;
        this.solution = solution;
        this.solutionImageAudioVideo = solutionImageAudioVideo;
        this.variationNumber = variationNumber;
    }

    // Getters and setters for all fields

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = ansType;
    }

    public int getTopicNo() {
        return topicNo;
    }

    public void setTopicNo(int topicNo) {
        this.topicNo = topicNo;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getWrongOption() {
        return wrongOption;
    }

    public void setWrongOption(String wrongOption) {
        this.wrongOption = wrongOption;
    }

    public String getWrongOption2() {
        return wrongOption2;
    }

    public void setWrongOption2(String wrongOption2) {
        this.wrongOption2 = wrongOption2;
    }

    public String getWrongOption3() {
        return wrongOption3;
    }

    public void setWrongOption3(String wrongOption3) {
        this.wrongOption3 = wrongOption3;
    }

    public String getTimeDoDQuestion() {
        return timeDoDQuestion;
    }

    public void setTimeDoDQuestion(String timeDoDQuestion) {
        this.timeDoDQuestion = timeDoDQuestion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolutionImageAudioVideo() {
        return solutionImageAudioVideo;
    }

    public void setSolutionImageAudioVideo(String solutionImageAudioVideo) {
        this.solutionImageAudioVideo = solutionImageAudioVideo;
    }

    public int getVariationNumber() {
        return variationNumber;
    }

    public void setVariationNumber(int variationNumber) {
        this.variationNumber = variationNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionType='" + questionType + '\'' +
                ", question='" + question + '\'' +
                ", ansType='" + ansType + '\'' +
                ", topicNo=" + topicNo +
                ", correctOption='" + correctOption + '\'' +
                ", wrongOption='" + wrongOption + '\'' +
                ", wrongOption2='" + wrongOption2 + '\'' +
                ", wrongOption3='" + wrongOption3 + '\'' +
                ", timeDoDQuestion='" + timeDoDQuestion + '\'' +
                ", image='" + image + '\'' +
                ", solution='" + solution + '\'' +
                ", solutionImageAudioVideo='" + solutionImageAudioVideo + '\'' +
                ", variationNumber=" + variationNumber +
                '}';
    }
}
