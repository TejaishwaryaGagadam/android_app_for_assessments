
package com.example.aishwaryagagadam.httpapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

//import okhttp3.Response;


public class StartActivity extends Activity {

    String loadQnsUrl = "http://kritiaishwaryaone.azurewebsites.net/api/quiz";
    ProgressBar pg;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        pg = (ProgressBar) findViewById(R.id.progressBar);
        tvTitle = (TextView) findViewById(R.id.textView);
        pg.setVisibility(View.VISIBLE);
        new RadioActivity(getApplicationContext()).get(loadQnsUrl,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pg.setVisibility(View.INVISIBLE);
                        try {
                            JSONObject j = new JSONObject(response);
                            String name = j.getString("Name");
                            tvTitle.setText(name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        startQuestions(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void startQuestions(String result) {
        Intent i = new Intent(getApplicationContext(), OneQuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Questions", result);
        bundle.putInt("score", 0);
        bundle.putInt("count", 0);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }

}