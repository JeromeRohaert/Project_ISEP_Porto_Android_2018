package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movies> {

    public MoviesAdapter(Context context, List<Movies> movies) {
        super(context,0, movies);
    }

    private class MoviesViewHolder {
        public TextView title;
        public TextView release_date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_movies,parent, false);
        }

        MoviesViewHolder viewHolder = (MoviesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MoviesViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.release_date = (TextView) convertView.findViewById(R.id.release);
            convertView.setTag(viewHolder);
        }

        return convertView;
    }

}
