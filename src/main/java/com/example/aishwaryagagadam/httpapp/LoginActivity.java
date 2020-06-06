package com.example.aishwaryagagadam.httpapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;


public class LoginActivity extends Activity {

    String LOGIN_URL = "http://kritiaishwaryaone.azurewebsites.net/api/users/%s/login/%s";
    ProgressBar pg;
    Button bLogin_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bRegister_1 = (Button) findViewById(R.id.button2);
        bLogin_1 = (Button) findViewById(R.id.button);
        pg = (ProgressBar) findViewById(R.id.progressBar);
        pg.setVisibility(View.INVISIBLE);

        if (bRegister_1 != null && bLogin_1 != null) {
            bLogin_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bLogin_1.setClickable(false);
                    login();
                }
            });

            bRegister_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    register();
                }
            });


        }
    }

    private void register() {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }

    private void login() {
        EditText etUser = (EditText) findViewById(R.id.editText);
        EditText etPass = (EditText) findViewById(R.id.editText2);

        final String username = etUser.getText().toString().replaceAll(" ", ""); // removing all whitespaces, cant have a space in the url
        final String password = etPass.getText().toString().replaceAll(" ", "");

        String url = String.format(LOGIN_URL, username, password);
        pg.setVisibility(View.VISIBLE);

        new RadioActivity(getApplicationContext()).get(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("true")) {
                    userLoggedIn(username);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed! Try again", Toast.LENGTH_SHORT).show();
                    bLogin_1.setClickable(true);

                }
                pg.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Login failed! Try again", Toast.LENGTH_SHORT).show();
                pg.setVisibility(View.INVISIBLE);
                bLogin_1.setClickable(true);

            }
        });

    }

    private void userLoggedIn(String username) {
        SharedPreferences sp = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        sp.edit().putBoolean("loggedIn", true).putString("user", "" + username).apply();
    }

}



