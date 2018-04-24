package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    public void OnSearch(View view){
        EditText editTextSearch = findViewById(R.id.editText_search);

        String query = String.valueOf(editTextSearch.getText());

        RequestQueue queueT = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/search/movie?api_key=8d4eebc9735f52f03dbf6c13a652b5c7&query="+query+"&page=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        mListView = (ListView) findViewById(R.id.list_movies);

                        List<Movies> movies = generateMovies();

                        MoviesAdapter adapter = new MoviesAdapter(List_Movies.this, movies);
                        mListView.setAdapter(adapter);

                        Toast.makeText(List_Movies.this, "It work", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(List_Movies.this, "Can't find", Toast.LENGTH_SHORT).show();
            }

        });
        queueT.add(stringRequest);
    }

    public List<Movies> generateMovies(){
        List<Movies> movies = new ArrayList<Movies>();
        movies.add(new Movies("Florent", "Mon premier tweet !"));
        movies.add(new Movies("Kevin", "C'est ici que ça se passe !"));
        movies.add(new Movies("Logan", "Que c'est beau..."));
        movies.add(new Movies("Mathieu", "Il est quelle heure ??"));
        movies.add(new Movies("Willy", "On y est presque"));
        movies.add(new Movies("Florent", "Mon premier tweet !"));
        movies.add(new Movies("Kevin", "C'est ici que ça se passe !"));
        movies.add(new Movies("Logan", "Que c'est beau..."));
        movies.add(new Movies("Mathieu", "Il est quelle heure ??"));
        movies.add(new Movies("Willy", "On y est presque"));
        movies.add(new Movies("Florent", "Mon premier tweet !"));
        movies.add(new Movies("Kevin", "C'est ici que ça se passe !"));
        movies.add(new Movies("Logan", "Que c'est beau..."));
        movies.add(new Movies("Mathieu", "Il est quelle heure ??"));
        movies.add(new Movies("Willy", "On y est presque"));
        return movies;
    }
}
