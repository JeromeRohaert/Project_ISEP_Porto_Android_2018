package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class index extends AppCompatActivity {

    Intent intentGet;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        intentGet = getIntent();
        email = intentGet.getStringExtra("emailGet");

    }

    public void deconnect (View view){
        finish();
    }

    public void listMovies(View view) {
        Intent intent = new Intent(this, List_Movies.class);
        intent.putExtra("emailGet",email);
        startActivity(intent);
    }

    public void recommandations(View view) {
        Intent intent = new Intent(this, recommandations.class);
        intent.putExtra("emailGet",email);
        startActivity(intent);
    }

    public void ratings(View view) {
        Intent intent = new Intent(this, Ratings.class);
        intent.putExtra("emailGet",email);
        startActivity(intent);
    }

}
