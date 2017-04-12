package com.example.witness.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.witness.popularmovies.utilities.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by witness on 2017-4-6.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{
    private List<Movie> mMovieData;
    private Movie movie;
    Context context;
    final private ListItemClickListener mOnClickListener;

    private final static String POSTER_BASE_URL="http://image.tmdb.org/t/p/w185/";

    public interface ListItemClickListener {
        void onListItemClick(Movie movie);
    }

    public MovieAdapter(List<Movie> mMovieData, Context context,ListItemClickListener listener) {
        this.mMovieData = mMovieData;
        this.context = context;
        this.mOnClickListener = listener;
    }

    public  class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition=getAdapterPosition();
            mOnClickListener.onListItemClick(mMovieData.get(clickedPosition));
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForListItem=R.layout.item;
        LayoutInflater inflater=LayoutInflater.from(context);

        View view=inflater.inflate(layoutIdForListItem,parent,false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
            String poster_url=POSTER_BASE_URL+mMovieData.get(position).getPoster();
            Picasso.with(context).load(poster_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }

}
