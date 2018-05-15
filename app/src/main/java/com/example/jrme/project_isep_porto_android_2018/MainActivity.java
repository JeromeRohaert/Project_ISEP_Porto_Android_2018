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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    String BASE_URL = "http://192.168.0.103:8080/IsepProject/";
    //String BASE_URL = "http://172.18.152.165:8080/IsepProject/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sign_up(View view) {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

/*
    // old version without server
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
*/
    public void sign_in (View view){

        final Intent intent = new Intent(this, index.class);
        final TextView mTextView = (TextView) findViewById(R.id.tv_signup);
        EditText emailT = (EditText) findViewById(R.id.email_login);
        final String emailTest = emailT.getText().toString();
        EditText passwordT = (EditText) findViewById(R.id.password_login);
        String passwordTest = passwordT.getText().toString();

            RequestQueue queueT = Volley.newRequestQueue(this);
            String urlT = BASE_URL+"VerifLogin?email="+emailTest+"&password="+passwordTest;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, urlT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response){
                            intent.putExtra("emailGet", emailTest);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "This account doesn't exist", Toast.LENGTH_SHORT).show();
                }

            });
            queueT.add(stringRequest);
    }

}
