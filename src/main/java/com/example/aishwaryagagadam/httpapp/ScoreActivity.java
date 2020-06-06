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
import android.widget.TextView;


public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int score = getIntent().getExtras().getInt("score");
        TextView tvScore = (TextView) findViewById(R.id.textView);
        tvScore.setText("Final Score: " + score);

        Button bStart = (Button) findViewById(R.id.button);
        if (bStart != null) {
            bStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    resetLogin();
                    Intent i = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }

    }

    private void resetLogin() {
        SharedPreferences sp = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        sp.edit().putBoolean("loggedIn", false).putString("user", "").apply();
    }
}
