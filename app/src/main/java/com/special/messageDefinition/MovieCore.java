package com.special.messageDefinition;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;


public class MovieCore {

    @Expose
    private List<String> sections = new ArrayList<String>();
    @Expose
    private List<List<Movie>> movies = new ArrayList<List<Movie>>();

    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    public List<Movie> getLastWeekMovies() {
        return movies.get(0);
    }

    public List<Movie> getThisWeekMovies() {
        return movies.get(1);
    }

    public void setMovies(List<List<Movie>> movies) {
        this.movies = movies;
    }

}

