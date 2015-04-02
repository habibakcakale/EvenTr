package com.special.core;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.special.DetailActivity;
import com.special.R;
import com.special.messageDefinition.Movie;
import com.special.messageDefinition.MovieCore;
import com.special.messageDefinition.MovieDetail;
import com.special.network.HttpGetAsyncTask;
import com.special.network.ImageLoadTask;
import com.special.utils.AsyncTaskClass;
import com.special.utils.Utilities;

import java.io.ByteArrayOutputStream;

/**
 * Created by CrimsonKing on 23.2.2015.
 */
public class InTheatersListFragmentActivity extends Fragment {

    private View parentView;
    private ListView listView;
    private Utilities utilities;
    private MovieCore movies;
    private InTheatersListAdapter inTheatersListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.eventr_intheaters_list, container, false);
        listView   = (ListView) parentView.findViewById(R.id.listView);

        utilities = new Utilities();
        utilities.showLoadingPanel(getActivity());

        new HttpGetAsyncTask(getActivity(), "http://www.sinemalar.com/json/mobile/playingMovies", new AsyncTaskClass.CallBack<String>() {
            @Override
            public void doJob(String params) {
                Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                movies = builder.fromJson(params, MovieCore.class);

                for(Movie m: movies.getLastWeekMovies()){
                    movies.getThisWeekMovies().add(m);
                }

                initView();

                for (Movie movie: movies.getThisWeekMovies())
                    new ImageLoadTask(inTheatersListAdapter, movie).execute(movie.getImage());
                utilities.hideLoadingPanel(getActivity());
            }
        }).execute();
        return parentView;
    }



    private void initView(){

        inTheatersListAdapter = new InTheatersListAdapter(getActivity(), R.layout.eventr_intheaters_list_item, movies.getThisWeekMovies());

        listView.setAdapter(inTheatersListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Movie mov = (Movie) listView.getAdapter().getItem(i);

                new HttpGetAsyncTask(getActivity(), "http://api.sinemalar.com/ajax/json/ios/v1/get/movie/" + mov.getId()+ "/1", new AsyncTaskClass.CallBack<String>() {
                    @Override
                    public void doJob(String params) {
                        Gson builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                        MovieDetail movieDetail = builder.fromJson(params, MovieDetail.class);

                        Bitmap bitmap= ((BitmapDrawable)mov.getDrawableImage()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] b = baos.toByteArray();

                        Intent intent = new Intent(getActivity(),DetailActivity.class);
                        intent.putExtra("movieDetail", movieDetail);
                        intent.putExtra("movImage", b);
                        startActivity(intent);
                    }
                }).execute();



            }
        });

    }


}
