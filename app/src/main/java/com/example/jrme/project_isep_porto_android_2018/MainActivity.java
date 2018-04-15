package com.example.jrme.project_isep_porto_android_2018;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sign_up(View view) {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

    public void sign_in(View view) {
        Intent intent = new Intent(this, index.class);
        startActivity(intent);
    }

}
