package com.example.witness.popularmovies.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by witness on 2017-4-6.
 */

public class OpenMoviesJsonUtils {
    public static List<Movie> getSimpleMoviesStringFromJson(Context context, String moviesJsonStr) throws JSONException {
        final String OWM_Results = "results";
        final String OWN_ORIGINAL_TITLE="original_title";
        final String OWN_POSTER_PATH="poster_path";
        final String OWN_OVERVIEW="overview";
        final String OWN_VOTE_AVERAGE="vote_average";
        final String OWN_REALEASE_DATE="release_date";
        final String OWN_ID="id";
        Movie movie;
        List<Movie> parseMovieData;
        JSONObject movieJson=new JSONObject(moviesJsonStr);

        JSONArray movieArray=movieJson.getJSONArray(OWM_Results);
        parseMovieData=new ArrayList<>();
        for(int i=0;i<movieArray.length();i++){
            String name;
            String poster;
            String overview;
            String popular;
            String release_date;
            String id;
            movie=new Movie();
            JSONObject movie_detail=movieArray.getJSONObject(i);

            movie.setId(movie_detail.getString(OWN_ID));
            movie.setName(movie_detail.getString(OWN_ORIGINAL_TITLE));
            movie.setPoster(movie_detail.getString(OWN_POSTER_PATH));
            movie.setOverview(movie_detail.getString(OWN_OVERVIEW));
            movie.setVote_average(movie_detail.getString(OWN_VOTE_AVERAGE));
            movie.setRelease_date(movie_detail.getString(OWN_REALEASE_DATE));
            parseMovieData.add(movie);

        }
        return parseMovieData;
    }
}
