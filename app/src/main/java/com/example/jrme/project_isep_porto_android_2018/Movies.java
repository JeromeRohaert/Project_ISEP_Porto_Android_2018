package com.example.jrme.project_isep_porto_android_2018;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Movies {

    private String id;
    private String title;
    private String release;
    private String poster;
    private String rating;


    public Movies(String id, String title, String release, String poster, String rating) {
        this.id = id;
        this.title = title;
        this.release = release;
        this.poster = poster;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String realese) {
        this.release = realese;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", release='" + release + '\'' +
                ", poster='" + poster + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
