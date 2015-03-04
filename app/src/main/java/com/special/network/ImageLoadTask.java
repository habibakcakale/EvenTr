package com.special.network;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.special.core.InTheatersListAdapter;
import com.special.messageDefinition.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Habib on 4.3.2015.
 */
public class ImageLoadTask extends AsyncTask<String, String, Drawable> {
    private InTheatersListAdapter  listAdapter;
    private Movie movie;

    public ImageLoadTask(InTheatersListAdapter inTheatersListAdapter, Movie movie){
        this.movie = movie;
        this.listAdapter = inTheatersListAdapter;
    }
    @Override
    protected Drawable doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            InputStream input =(InputStream) url.getContent();
            return Drawable.createFromStream(input,null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Drawable bitmap) {
        movie.setDrawableImage(bitmap);
        if(listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
    }
}