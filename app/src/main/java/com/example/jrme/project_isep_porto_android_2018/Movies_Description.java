package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movies_Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies__description);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        final TextView tv = findViewById(R.id.textView2);

        RequestQueue queueT = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/movie/"+id+"?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&language=en-US";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject description = new JSONObject(response);
                            String title = description.getString("title");
                            tv.setText(title);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        });

        queueT.add(stringRequest);

    }
}
