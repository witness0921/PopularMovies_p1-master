package com.example.witness.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.witness.popularmovies.utilities.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private Movie movie;
    private final static String POSTER_BASE_URL="http://image.tmdb.org/t/p/w500/";
    Context context;
    ImageView imageView_detail;
    TextView textView_title_name_detail;
    TextView textView_vote_average_detail;
    TextView textView_released_date_detail;
    TextView textView_overview_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        Resources resources=getResources();
        movie= (Movie) intent.getSerializableExtra("detail_movie");
        imageView_detail= (ImageView)findViewById(R.id.iv_poster_detail);
        textView_title_name_detail= (TextView)findViewById(R.id.tv_title_name_detail);
        textView_vote_average_detail= (TextView)findViewById(R.id.tv_vote_average_detail);
        textView_released_date_detail= (TextView)findViewById(R.id.tv_released_date_detail);
        textView_overview_detail= (TextView)findViewById(R.id.tv_overview_detail);

        String poster_url=POSTER_BASE_URL+movie.getPoster();
        Picasso.with(context).load(poster_url).into(imageView_detail);
        textView_title_name_detail.setText(String.format(resources.getString(R.string.title_name))+movie.getName());
        textView_vote_average_detail.setText(String.format(resources.getString(R.string.vote_average))+movie.getVote_average());
        textView_released_date_detail.setText(String.format(resources.getString(R.string.release_date))+movie.getRelease_date());
        textView_overview_detail.setText(String.format(resources.getString(R.string.overview))+movie.getOverview());
    }

}
