package com.example.jrme.project_isep_porto_android_2018;

public class Movies {

    private String id;
    private String title;
    private String release;

    public Movies(String id, String title, String release) {
        this.id = id;
        this.title = title;
        this.release = release;
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



}
