package com.example.aishwaryagagadam.httpapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abs on 29/4/16.
 */
public class Question {

    int id;
    String questionText, optionA, optionB, optionC, optionD, rightAnswer, selectedAnswer;

    public Question(int id, String optionA, String optionB, String optionC, String optionD, String questionText, String rightAnswer, String selectedAnswer) {
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.questionText = questionText;
        this.rightAnswer = rightAnswer;
        this.selectedAnswer = selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public int getId() {
        return id;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

}
