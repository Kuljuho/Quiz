package com.quiz.quiz;

public class Answer {
    private int questionId;
    private int selectedOptionIndex;

    public Answer(int questionId, int selectedOptionIndex) {
        this.questionId = questionId;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSelectedOptionIndex() {
        return this.selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

}
