package com.special.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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

import com.pnikosis.materialishprogress.ProgressWheel;
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
        viewHolder.imageDescription = (TextView) v.findViewById(R.id.item_description);
        viewHolder.image = (ImageView) v.findViewById(R.id.item_image);
        viewHolder.orgName = (TextView) v.findViewById(R.id.orgName);
        viewHolder.director = (TextView) v.findViewById(R.id.directorText);
        viewHolder.loadingPanel = (ProgressWheel) v.findViewById(R.id.loadingPanel);

        Movie movie = getItem(position);
        //new ImageLoadTask(viewHolder.image).execute(movie.getImage());


        viewHolder.type.setText(movie.getType());
        viewHolder.director.setText(movie.getDirector());
        viewHolder.orgName.setText(movie.getOrgName());
        viewHolder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        viewHolder.image.setAdjustViewBounds(true);
        viewHolder.image.setImageDrawable(movie.getDrawableImage());


        if(movie.getName().length() > 35){
            viewHolder.title.setText(movie.getName().substring(0,34) + "...");
        }else if(movie.getName().equals("")){
            viewHolder.title.setText(movie.getOrgName());
        }else{
            viewHolder.title.setText(movie.getName());
        }

        if(movie.getOrgName().equals("")){
            viewHolder.title.setText(movie.getName());
        }

        return v;
    }

    public Bitmap highlightImage(Bitmap src) {
        // create new bitmap, which will be painted and becomes result image
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 96, src.getHeight() + 96, Bitmap.Config.ARGB_8888);
        // setup canvas for painting
        Canvas canvas = new Canvas(bmOut);
        // setup default color
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        // create a blur paint for capturing alpha
        Paint ptBlur = new Paint();
        ptBlur.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
        int[] offsetXY = new int[2];
        // capture alpha into a bitmap
        Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
        // create a color paint
        Paint ptAlphaColor = new Paint();
        ptAlphaColor.setColor(0xFFFFFFFF);
        // paint color for captured alpha region (bitmap)
        canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
        // free memory
        bmAlpha.recycle();

        // paint the image source
        canvas.drawBitmap(src, 0, 0, null);

        // return out final image
        return bmOut;
    }

    class ViewHolder {
        TextView title;
        TextView type;
        TextView imageDescription;
        TextView orgName;
        TextView director;
        ImageView image;
        ProgressWheel loadingPanel;
        Bitmap b;
        int position;
    }
}
