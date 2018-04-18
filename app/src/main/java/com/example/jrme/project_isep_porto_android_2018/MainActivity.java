package com.example.jrme.project_isep_porto_android_2018;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText email = findViewById(R.id.email_login);
        EditText password = findViewById(R.id.password_login);

        String verif_email = String.valueOf(email.getText());
        String verif_password = String.valueOf(password.getText());

        if(verif_email.equals("meow") && verif_password.equals("meow")){
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Informations");
            alertDialog.setMessage("email or password false");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",

                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
