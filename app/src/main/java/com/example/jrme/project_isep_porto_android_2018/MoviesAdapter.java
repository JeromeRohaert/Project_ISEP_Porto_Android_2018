package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movies> {

    public MoviesAdapter(Context context, List<Movies> movies) {
        super(context,0, movies);
    }

    private class MoviesViewHolder {
        public TextView row_title;
        public TextView row_release_date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_movies,parent, false);
        }

        MoviesViewHolder viewHolder = (MoviesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MoviesViewHolder();
            viewHolder.row_title = (TextView) convertView.findViewById(R.id.row_title);
            viewHolder.row_release_date = (TextView) convertView.findViewById(R.id.row_release);
            convertView.setTag(viewHolder);
        }

        Movies movieG = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.row_title.setText(movieG.getTitle());
        viewHolder.row_release_date.setText(movieG.getRelease());

        return convertView;
    }
}
