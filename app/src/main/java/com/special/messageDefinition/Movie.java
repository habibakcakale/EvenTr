package com.special.messageDefinition;

import com.special.R;
import com.special.core.InTheatersListAdapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Movie {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String orgName;
    @Expose
    private String image;
    @Expose
    private String rating;
    @Expose
    private String type;
    @Expose
    private String director;

    private Drawable drawableImage;
    public Drawable getDrawableImage(){
    return drawableImage;
}
    public void setDrawableImage(Drawable drawableImage){
        this.drawableImage = drawableImage;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


}