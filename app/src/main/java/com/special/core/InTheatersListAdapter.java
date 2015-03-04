package com.special.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.special.R;
import com.special.messageDefinition.Movie;
import com.special.messageDefinition.MovieCore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CrimsonKing on 23.2.2015.
 */
public class InTheatersListAdapter extends ArrayAdapter<Movie> {

    //ViewHolder viewHolder;

    //private MovieCore mMovies;
    private Context mContext;
    private int mScreenWidth;
    private List<Movie> movies;
    public InTheatersListAdapter(Context context, int resource, Movie[] objects) {
        super(context, resource, objects);
        movies = new ArrayList<Movie>() ;
        for (Movie movie: objects)
        movies.add(movie);
    }

    public InTheatersListAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        movies = objects;
    }
    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.eventr_intheaters_list_item, null);
        }
        viewHolder.title = (TextView) v.findViewById(R.id.item_title);
        viewHolder.type = (TextView) v.findViewById(R.id.typeText);
        viewHolder.rating = (TextView) v.findViewById(R.id.item_rating);
        viewHolder.imageDescription = (TextView) v.findViewById(R.id.item_description);
        viewHolder.image = (ImageView) v.findViewById(R.id.item_image);
        viewHolder.orgName = (TextView) v.findViewById(R.id.orgName);
        viewHolder.director = (TextView) v.findViewById(R.id.directorText);
        viewHolder.loadingPanel = (RelativeLayout) v.findViewById(R.id.loadingPanel);

        Movie movie = getItem(position);
        //new ImageLoadTask(viewHolder.image).execute(movie.getImage());
        viewHolder.title.setText(movie.getName());
        viewHolder.type.setText(movie.getType());
        viewHolder.director.setText(movie.getDirector());
        viewHolder.orgName.setText(movie.getOrgName());
        viewHolder.rating.setText(String.format("Puan : %s/10", movie.getRating()));

        viewHolder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewHolder.image.setAdjustViewBounds(true);
        Drawable movieImage = movie.getDrawableImage();
        viewHolder.image.setImageDrawable(movieImage);
        return v;
    }

    class ViewHolder {
        TextView title;
        TextView type;
        TextView rating;
        TextView imageDescription;
        TextView orgName;
        TextView director;
        ImageView image;
        RelativeLayout loadingPanel;
        Bitmap b;
        int position;
    }
}
