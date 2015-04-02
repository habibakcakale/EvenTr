package com.special;

import com.special.core.ArtistListAdapter;
import com.special.messageDefinition.Artist;
import com.special.messageDefinition.MovieDetail;
import com.special.network.ArtistImageLoadTask;
import com.special.utils.UIParallaxScroll;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	//Views
	LinearLayout topview;
	TextView titleBar;
    ImageView movieImage;
    TextView orgName;
    ListView artistListView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
        Drawable noPicture = getResources().getDrawable(R.drawable.no_picture);

        artistListView = (ListView) findViewById(R.id.movie_detail_artistList);

        ((UIParallaxScroll) findViewById(R.id.scroller)).setOnScrollChangedListener(mOnScrollChangedListener);

        topview = (LinearLayout) findViewById(R.id.layout_top);
	    titleBar = (TextView) findViewById(R.id.movie_detail_titleBar);

	    //Setting the titlebar background blank (initial state)
	    topview.getBackground().setAlpha(0);
	    titleBar.setVisibility(View.INVISIBLE);
	    
	    Intent intent = getIntent();
	    MovieDetail movie = (MovieDetail) intent.getSerializableExtra("movieDetail");

        ArtistListAdapter artistListAdapter = new ArtistListAdapter(getApplicationContext(), R.layout.eventr_intheaters_movie_detail_artist, movie.getArtists());

        artistListView.setAdapter(artistListAdapter);

        final float scale = getResources().getDisplayMetrics().density;
        int layWidth = (int) (160 * scale + 0.5f);


        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) artistListView.getLayoutParams();
        lp.height = movie.getArtists().size() * layWidth;
        artistListView.setLayoutParams(lp);

        for (Artist a: movie.getArtists()){
            if(!a.getImage().equals("http://www.sinemalar.com/img/afisYok/AvatarYOK2.jpg")){
                new ArtistImageLoadTask(artistListAdapter, a).execute(a.getImage());
            }else{
                a.setDrawableImage(noPicture);
            }
        }


        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("movImage");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

        orgName = (TextView) findViewById(R.id.movie_detail_orgName);
        TextView name = (TextView) findViewById(R.id.movie_detail_name);
        TextView type = (TextView) findViewById(R.id.movie_detail_type);
        TextView director = (TextView) findViewById(R.id.movie_detail_director);
        TextView description = (TextView) findViewById(R.id.movie_detail_summary);
        movieImage = (ImageView) findViewById(R.id.movie_detail_image);
        TextView duration = (TextView) findViewById(R.id.movie_detail_duration);


        orgName.setText(movie.getMovie().getOrgName());
        name.setText(movie.getMovie().getName());
        type.setText(movie.getMovie().getType());
        director.setText(movie.getMovie().getDirector());
        description.setText(movie.getMovie().getSummary());
        movieImage.setImageBitmap(bmp);
        titleBar.setText(movie.getMovie().getOrgName());
        duration.setText(movie.getMovie().getDuration());

        movieImage.setScaleType(ImageView.ScaleType.FIT_XY);
        movieImage.setAdjustViewBounds(true);


	    Button btnback = (Button) findViewById(R.id.title_bar_left_menu);
	    btnback.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				onBackPressed();
		}
	    	
	    });
	}
	
	@Override
	public void onStop(){
		super.onStop();
        movieImage.setImageResource(0);
	}
	
	//performing changes to the titlebars visibility
	private UIParallaxScroll.OnScrollChangedListener mOnScrollChangedListener = new UIParallaxScroll.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        	//At this height, the title has to be fully visible
        	final int headerHeight = (findViewById(R.id.movie_detail_image).getHeight() + orgName.getHeight()) - topview.getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            topview.getBackground().setAlpha(newAlpha);
            topview.getBackground().setAlpha(newAlpha);
            
            Animation animationFadeIn = AnimationUtils.loadAnimation(DetailActivity.this,R.anim.fadein);
            Animation animationFadeOut = AnimationUtils.loadAnimation(DetailActivity.this,R.anim.fadeout);
            
            if (newAlpha == 255 && titleBar.getVisibility() != View.VISIBLE && !animationFadeIn.hasStarted()){
            	titleBar.setVisibility(View.VISIBLE);
            	titleBar.startAnimation(animationFadeIn);
            } else if (newAlpha < 255 && !animationFadeOut.hasStarted() && titleBar.getVisibility() != View.INVISIBLE)  { 	
            	titleBar.startAnimation(animationFadeOut);
            	titleBar.setVisibility(View.INVISIBLE);
            }
        }
    };
}
