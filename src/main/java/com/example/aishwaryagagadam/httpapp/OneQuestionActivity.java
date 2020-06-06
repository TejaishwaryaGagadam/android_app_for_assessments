package com.example.aishwaryagagadam.httpapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aishwaryagagadam.httpapp.data.Parser;
import com.example.aishwaryagagadam.httpapp.data.Question;

import java.util.ArrayList;
import java.util.List;


public class OneQuestionActivity extends Activity {

    private int count, score;
    TextView tQ;
    ArrayList<Question> questions;
    String responseQuestions;
    private Question q;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_question);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            count = b.getInt("count");
            score = b.getInt("score");

            questions = Parser.parse(responseQuestions = b.getString("Questions"));
            final Button bNext = (Button) findViewById(R.id.button);
            tQ = (TextView) findViewById(R.id.textViewQ);

            q = questions.get(count);
            tQ.setText(q.getQuestionText());

            rg = (RadioGroup) findViewById(R.id.radioGroup);
            addOptions(rg);

            if (bNext != null) {
                if (count == questions.size() - 1) {
                    bNext.setText("Submit");
                } else {
                    bNext.setText("Next");
                }

                bNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (rg.isSelected()) {
                            Toast.makeText(getApplicationContext(), "Please select an option!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        computeScore();
                        if (count == questions.size() - 1) {
                            Intent i = new Intent(getApplicationContext(), ScoreActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("score", score);
                            i.putExtras(bundle);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(getApplicationContext(), OneQuestionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Questions", responseQuestions);
                            bundle.putInt("count", count + 1);
                            bundle.putInt("score", score);
                            i.putExtras(bundle);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }


            SharedPreferences sp = getSharedPreferences("MY_PREF", MODE_PRIVATE);
            String username = sp.getString("user", "");
            TextView tvUser = (TextView) findViewById(R.id.textViewUser);
            tvUser.setText(username);
        }
    }

    private void computeScore() {
        int id = rg.getCheckedRadioButtonId();
        String answerSelected = "";
        if (id == 10001) {
            answerSelected = q.getOptionA();
        } else if (id == 10002) {
            answerSelected = q.getOptionB();
        } else if (id == 10003) {
            answerSelected = q.getOptionC();
        } else if (id == 10004) {
            answerSelected = q.getOptionD();
        }
        System.out.println("ansSel: " + answerSelected + " | qAns: " + q.getRightAnswer());
        if (answerSelected.equals(q.getRightAnswer())) {
            score += 1;
        }
    }

    @SuppressWarnings("ResourceType")
    private void addOptions(RadioGroup rg) {
        rg.setOrientation(RadioGroup.VERTICAL);

        RadioButton rbA = new RadioButton(this);
        rbA.setText(" " + q.getOptionA());
        rbA.setId(10001);
        RadioButton rbB = new RadioButton(this);
        rbB.setText(" " + q.getOptionB());
        rbB.setId(10002);
        RadioButton rbC = new RadioButton(this);
        rbC.setText(" " + q.getOptionC());
        rbC.setId(10003);
        RadioButton rbD = new RadioButton(this);
        rbD.setText(" " + q.getOptionD());
        rbD.setId(10004);

        rg.addView(rbA);
        rg.addView(rbB);
        rg.addView(rbC);
        rg.addView(rbD);

        rg.invalidate();
    }

}


