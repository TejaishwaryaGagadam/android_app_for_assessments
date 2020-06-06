package com.example.aishwaryagagadam.httpapp.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abs on 29/4/16.
 */
public class Parser {

    public static ArrayList<Question> parse(String result) {
        try {
            JSONObject j = new JSONObject(result);
            String name = j.getString("Name");
            JSONArray jArr = j.getJSONArray("Questions");
            ArrayList<Question> questions = new ArrayList<Question>();

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jQ = (JSONObject) jArr.get(i);
                int id = jQ.getInt("Id");
                String optionA = jQ.getString("OptionA");
                String optionB = jQ.getString("OptionB");
                String optionC = jQ.getString("OptionC");
                String optionD = jQ.getString("OptionD");
                String questionText = jQ.getString("QuestionText");
                String rightAnswer = jQ.getString("RightAnswer");
                String selectedAnswer = null;
                Question q = new Question(id, optionA, optionB, optionC, optionD, questionText, rightAnswer, selectedAnswer);
                questions.add(q);
            }
            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
