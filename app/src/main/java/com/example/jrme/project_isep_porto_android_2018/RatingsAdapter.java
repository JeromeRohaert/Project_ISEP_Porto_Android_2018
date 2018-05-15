package com.example.jrme.project_isep_porto_android_2018;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RatingsAdapter extends ArrayAdapter<Movies> {

    public RatingsAdapter(Context context, List<Movies> movies) {
        super(context,0, movies);
    }

    private class MoviesViewHolder {
        public TextView row_title;
        public TextView row_rating;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_ratings,parent, false);
        }

        MoviesViewHolder viewHolder = (MoviesViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MoviesViewHolder();
            viewHolder.row_title = (TextView) convertView.findViewById(R.id.tv_title_rating);
            viewHolder.row_rating = (TextView) convertView.findViewById(R.id.tv_rating_rating);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Movies movieG = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.row_title.setText(movieG.getTitle());
        viewHolder.row_rating.setText(movieG.getRating()+" / 10");

        return convertView;
    }
}
