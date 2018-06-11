package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Ratings extends AppCompatActivity {

    Intent intent;
    String email;
    String title;

    String BASE_URL = "http://192.168.0.103:8080/IsepProject/";
    //String BASE_URL = "http://172.18.157.63:8080/IsepProject/";
    String API_KEY = "8d4eebc9735f52f03dbf6c13a652b5c7";
    Context context = Ratings.this;
    ListView mListView;

    // fid, score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        intent = getIntent();
        email = intent.getStringExtra("emailGet");

        RequestQueue queueT = Volley.newRequestQueue(this);
        String url = BASE_URL+"Ratings?email="+email;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        mListView = (ListView) findViewById(R.id.list_ratings);

                        List<Movies> movies = generateListRatings(response);

                        RatingsAdapter adapter = new RatingsAdapter(context, movies);

                        mListView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent_description = new Intent(context, Movies_Description.class);
                                Movies listItem = (Movies) mListView.getItemAtPosition(position);
                                intent_description.putExtra("id",listItem.getId());
                                intent_description.putExtra("emailGet",email);
                                startActivity(intent_description);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Can't find", Toast.LENGTH_SHORT).show();
            }

        });
        queueT.add(stringRequest);
    }

    public List<Movies> generateListRatings(String response){
        final List<Movies> list = new ArrayList<Movies>();
        JSONArray obj;

            try {
                obj = new JSONArray(response);

                for (int i = 0; i < obj.length(); i++) {
                    JSONObject c = obj.getJSONObject(i);

                    final String fid = c.getString("fid");
                    final String score = c.getString("score");

                    //===========

                    RequestQueue queue = Volley.newRequestQueue(this);
                    String url = "https://api.themoviedb.org/3/movie/" + fid + "?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&language=en-US";


                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                        public void onResponse(String response) {
                                            try {
                                                TextView tv_title = findViewById(R.id.tv_title_rating);
                                                JSONObject description = new JSONObject(response);
                                                String title = description.getString("title");
                                                tv_title.setText("Title");
                                                list.add(new Movies(fid, title, null, null, score));
                                                Thread.sleep(1);


                                    } catch (JSONException e) {
                                   } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.networkResponse == null) {
                                if (error.getClass().equals(TimeoutError.class)) {
                                    // Show timeout error message
                                    Toast.makeText(Ratings.this, "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                    });

                    queue.add(stringRequest);

                    // =========

                    if(list.isEmpty()) {
                        list.add(new Movies("ID", "Title", null, null, "Score"));
                    }
                }

            } catch (Throwable t) {
            }

        try{
        } catch (Exception e){}
        return list;
    }
}
