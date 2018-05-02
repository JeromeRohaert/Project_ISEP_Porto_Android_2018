package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class List_Movies extends AppCompatActivity {

    String API_KEY = "8d4eebc9735f52f03dbf6c13a652b5c7";
    Context context = List_Movies.this;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__movies);
    }

    public List<Movies> generateList(String response){
        List<Movies> list = new ArrayList<Movies>();

        JSONObject obj;
        try {
            obj = new JSONObject(response);
            JSONArray results = obj.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject c = results.getJSONObject(i);
                String title = c.getString("title");
                String release_date = c.getString("release_date");
                String id = c.getString("id");
                list.add(new Movies(id,title,release_date));
            }

        } catch (Throwable t) {}

        return list;
    }

    public void OnSearchTestMeow(View view){
        EditText editTextSearch = findViewById(R.id.editText_search);

        String query = String.valueOf(editTextSearch.getText());

        RequestQueue queueT = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/search/movie?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&query="+query+"&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        mListView = (ListView) findViewById(R.id.list_movies);

                        List<Movies> movies = generateList(response);

                        MoviesAdapter adapter = new MoviesAdapter(List_Movies.this, movies);
                        mListView.setAdapter(adapter);

                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(List_Movies.this, Movies_Description.class);
                                Movies listItem = (Movies) mListView.getItemAtPosition(position);
                                intent.putExtra("id",listItem.getId());
                                startActivity(intent);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(List_Movies.this, "Can't find", Toast.LENGTH_SHORT).show();
            }

        });
        queueT.add(stringRequest);
    }
}
