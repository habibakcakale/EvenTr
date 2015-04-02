package com.special.messageDefinition;
import android.graphics.drawable.Drawable;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

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

    @Expose
    private String summary;
    @Expose
    private String duration;
    @Expose
    private String produceYear;
    @Expose
    private String week;
    @Expose
    private String pubDate;
    @Expose
    private String embedId;
    @Expose
    private String embedTitle;
    @Expose
    private String trailerUrl;


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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDuration(){
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(String produceYear) {
        this.produceYear = produceYear;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getEmbedId() {
        return embedId;
    }

    public void setEmbedId(String embedId) {
        this.embedId = embedId;
    }

    public String getEmbedTitle() {
        return embedTitle;
    }

    public void setEmbedTitle(String embedTitle) {
        this.embedTitle = embedTitle;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

}

