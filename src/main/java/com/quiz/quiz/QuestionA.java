package com.quiz.quiz;

import java.util.List;

public class QuestionA {
    private int id;
    private String questionText;
    private List<String> options;

    public QuestionA(int id, String questionText, List<String> options) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
}
