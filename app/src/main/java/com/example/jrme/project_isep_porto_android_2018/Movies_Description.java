package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movies_Description extends AppCompatActivity {

    String BASE_URL = "http://192.168.0.103:8080/IsepProject/";
    //String BASE_URL = "http://172.18.152.165:8080/IsepProject/";
    // Rating bar
    RatingBar rating;
    String email;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies__description);
        Intent intent = getIntent();
        email = intent.getStringExtra("emailGet");
        id = intent.getStringExtra("id");
        final TextView tv = findViewById(R.id.tv_title);
        final ImageView iv = findViewById(R.id.iv_poster);
        final TextView tv_description = findViewById(R.id.tv_description);
        rating = findViewById(R.id.ratingBar);

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
                            String url_poster_string = "http://image.tmdb.org/t/p/w500/"+ description.getString("poster_path");
                            Picasso.with(Movies_Description.this).load(url_poster_string+"").into(iv);
                            String s_descritpion = description.getString("overview");
                            tv_description.setText(s_descritpion);
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

    public void OnSend (View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BASE_URL+"Ratings"; // TODO change url
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                Toast.makeText(Movies_Description.this, "Score added", Toast.LENGTH_SHORT).show();
                finish();
                }},
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        // Show timeout error message
                        Toast.makeText(Movies_Description.this, "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                    }
                }
            }}){
            @Override
            protected Map<String, String> getParams() {
                int score = (int) (rating.getRating()*2);

                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("fid",id);
                params.put("score", String.valueOf(score));
                return params;
            }
        };
        queue.add(postRequest);
    }
}
