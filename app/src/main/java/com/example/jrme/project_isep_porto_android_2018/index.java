package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void deconnect (View view){
        finish();
    }


    public void listMovies(View view) {
        Intent intent = new Intent(this, List_Movies.class);
        startActivity(intent);
    }

    public void recommandations(View view) {
        Intent intent = new Intent(this, recommandations.class);
        startActivity(intent);
    }

}
