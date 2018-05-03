package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
import java.util.List;

public class Movies_Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies__description);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final TextView tv = findViewById(R.id.tv_title);
        final ImageView iv = findViewById(R.id.iv_poster);
        final TextView tv_description = findViewById(R.id.tv_description);

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

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Movies_Description.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
