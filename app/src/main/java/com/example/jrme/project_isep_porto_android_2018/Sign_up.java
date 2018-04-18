package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {

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

    public void post_user(View v){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://172.18.157.53:8080/IsepProject/Register"; // TODO change url
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }){
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

    }


  /*  public void test (View v){


        final TextView mTextView = (TextView) findViewById(R.id.tv_signup);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://172.18.157.53:8080/IsepProject/Test";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
    */

}
