package com.example.jrme.project_isep_porto_android_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {

    String BASE_URL = "http://192.168.0.102:8080/IsepProject/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void close_signup (View view){
        finish();
    }

    public JSONObject getJsonObject() throws AuthFailureError {

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText first_name = (EditText) findViewById(R.id.first_name);
        EditText last_name = (EditText) findViewById(R.id.last_name);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", String.valueOf(email.getText()));
        params.put("pwd", String.valueOf(String.valueOf(password.getText()).hashCode()));
        params.put("first", String.valueOf(first_name.getText()));
        params.put("last", String.valueOf(last_name.getText()));

        JSONObject json = new JSONObject(params);
        return json;
    }

    public void post_user(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BASE_URL+"Register"; // TODO change url
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){}},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}}){
            @Override
            protected Map<String, String> getParams() {
                EditText email = (EditText) findViewById(R.id.email);
                EditText password = (EditText) findViewById(R.id.password);
                EditText first_name = (EditText) findViewById(R.id.first_name);
                EditText last_name = (EditText) findViewById(R.id.last_name);

                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", String.valueOf(email.getText()));
                params.put("pwd", String.valueOf(password.getText()));
                params.put("first", String.valueOf(first_name.getText()));
                params.put("last", String.valueOf(last_name.getText()));

                return params;
            }
        };
        queue.add(postRequest);
        Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void post_user_test(View v){

        final TextView mTextView = (TextView) findViewById(R.id.tv_signup);
        EditText emailT = (EditText) findViewById(R.id.email);
        String emailTest = emailT.getText().toString();

        RequestQueue queueT = Volley.newRequestQueue(this);
        String urlT = BASE_URL+"RegisterVerif?email="+emailTest;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(Sign_up.this, "Email already used", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                post_user();
            }

        });
        queueT.add(stringRequest);
    }
}


