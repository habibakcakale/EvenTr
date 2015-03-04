package com.special.core;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.special.DetailActivity;
import com.special.MainActivity;
import com.special.R;
import com.special.messageDefinition.Movie;
import com.special.messageDefinition.MovieCore;
import com.special.network.HttpGetAsyncTask;
import com.special.network.ImageLoadTask;
import com.special.utils.AsyncTaskClass;
import com.special.utils.Utilities;

import java.util.List;

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
                initView();
                for (Movie movie: movies.getMovies())
                    new ImageLoadTask(inTheatersListAdapter, movie).execute(movie.getImage());
                utilities.hideLoadingPanel(getActivity());
            }
        }).execute();
        return parentView;
    }



    private void initView(){

        //Getting width of display, could be usefull for scaling bitmaps
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else{
            width = display.getWidth();
        }
        inTheatersListAdapter = new InTheatersListAdapter(getActivity(), R.layout.eventr_intheaters_list_item, movies.getMovies());

        listView.setAdapter(inTheatersListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Movie mov = (Movie) listView.getAdapter().getItem(i);

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("title", mov.getName());
                //intent.putExtra("img", item.getImageId());
                //intent.putExtra("desc r", item.getDesc());
                startActivity(intent);
            }
        });

    }


}
