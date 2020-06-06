package com.example.aishwaryagagadam.httpapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class  RadioActivity{

    public static RequestQueue requestQueue = null;

    public RadioActivity(Context context){
        if(requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
    }

    public void get(String url,Response.Listener<String> l, Response.ErrorListener el){
        StringRequest sr = new StringRequest(Request.Method.GET, url, l, el);
        if(requestQueue != null)
            requestQueue.add(sr);
    }

}

