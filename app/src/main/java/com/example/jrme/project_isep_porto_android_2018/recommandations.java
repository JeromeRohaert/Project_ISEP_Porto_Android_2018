package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class recommandations extends AppCompatActivity {

    String email;
    //String BASE_URL = "http://192.168.0.102:8080/IsepProject/";
    String BASE_URL = "http://172.18.159.125:8080/IsepProject/";
    //String BASE_URL = "http://172.18.159.82:8080/IsepProject/";
           // String BASE_URL = "http://172.18.154.229:8080/IsepProject/";
    TextView tv;
    ImageView iv;
    TextView tv2;
    ImageView iv2;
    TextView tv3;
    ImageView iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommandations);
        Intent intent = getIntent();
        email = intent.getStringExtra("emailGet");

        final String [] ids = new String [3];

        tv = findViewById(R.id.tv_title1);
        iv = findViewById(R.id.iv_poster1);
        tv2 = findViewById(R.id.tv_title2);
        iv2 = findViewById(R.id.iv_poster2);
        tv3 = findViewById(R.id.tv_title3);
        iv3 = findViewById(R.id.iv_poster3);

        RequestQueue queueT = Volley.newRequestQueue(this);
        String url = BASE_URL+"Recommandations?email="+email;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        String [] split1 = response.split(",");
                        for(int i = 0; i<split1.length;i++){
                            String [] split2 = split1[i].split("\"");
                            String id = split2[1];
                            ids[i] = id;

                            RequestQueue queue1 = Volley.newRequestQueue(recommandations.this);
                            String url1 = "https://api.themoviedb.org/3/movie/"+ids[0]+"?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&language=en-US";

                            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response){
                                            try {

                                                    JSONObject description = null;
                                                    description = new JSONObject(response);
                                                    String title = description.getString("title");
                                                    tv.setText(title);
                                                    String url_poster_string = "http://image.tmdb.org/t/p/w500/" + description.getString("poster_path");
                                                    Picasso.with(recommandations.this).load(url_poster_string + "").into(iv);

                                                iv.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent intent = new Intent(recommandations.this, Movies_Description.class);
                                                        intent.putExtra("emailGet",email);
                                                        intent.putExtra("id",ids[0]);
                                                        postStats();
                                                        startActivity(intent);
                                                    }
                                                });

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });
                            queue1.add(stringRequest1);
                        }
//============================================================================================================================================================================

                        RequestQueue queue2 = Volley.newRequestQueue(recommandations.this);
                        String url2 = "https://api.themoviedb.org/3/movie/"+ids[1]+"?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&language=en-US";

                        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response){
                                        try {

                                            JSONObject description = null;
                                            description = new JSONObject(response);
                                            String title = description.getString("title");
                                            tv2.setText(title);
                                            String url_poster_string = "http://image.tmdb.org/t/p/w500/" + description.getString("poster_path");
                                            Picasso.with(recommandations.this).load(url_poster_string + "").into(iv2);

                                            iv2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(recommandations.this, Movies_Description.class);
                                                    intent.putExtra("emailGet",email);
                                                    intent.putExtra("id",ids[1]);
                                                    postStats();
                                                    startActivity(intent);
                                                }
                                            });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        queue2.add(stringRequest2);
//=================================================================================================================================

                        RequestQueue queue3 = Volley.newRequestQueue(recommandations.this);
                        String url3 = "https://api.themoviedb.org/3/movie/"+ids[2]+"?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&language=en-US";

                        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url3,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response){
                                        try {

                                            JSONObject description = null;
                                            description = new JSONObject(response);
                                            String title = description.getString("title");
                                            tv3.setText(title);
                                            String url_poster_string = "http://image.tmdb.org/t/p/w500/" + description.getString("poster_path");
                                            Picasso.with(recommandations.this).load(url_poster_string + "").into(iv3);

                                            iv3.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(recommandations.this, Movies_Description.class);
                                                    intent.putExtra("emailGet",email);
                                                    intent.putExtra("id",ids[2]);
                                                    postStats();
                                                    startActivity(intent);
                                                }
                                            });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        queue3.add(stringRequest3);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("ERROR");
            }

        });
        queueT.add(stringRequest);
    }

    public void postStats(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BASE_URL+"Recommandations";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(postRequest);
    }

    public void OnClickNotInteresting(View v){
        finish();
    }

}
