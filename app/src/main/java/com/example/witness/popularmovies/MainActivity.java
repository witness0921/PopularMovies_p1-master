package com.example.witness.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.witness.popularmovies.utilities.Movie;
import com.example.witness.popularmovies.utilities.NetworkUtils;
import com.example.witness.popularmovies.utilities.OpenMoviesJsonUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
    private Movie movie;
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private TextView textView_error_msg;
    private ProgressBar progressBar;
    private MovieAdapter adapter;
    private String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviwe);
        textView_error_msg= (TextView) findViewById(R.id.tv_error_msg);
        progressBar= (ProgressBar) findViewById(R.id.pb);
        new FetchMovieTask().execute(query);
    }


    @Override
    public void onListItemClick(Movie movie) {
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("detail_movie",movie);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                query = "popular";
                new FetchMovieTask().execute(query);
                break;
            case R.id.top_rated:
                query = "top_rated";
                new FetchMovieTask().execute(query);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            textView_error_msg.setVisibility(View.INVISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            String sort = params[0];

            URL movierRequestUrl = NetworkUtils.builderUrl(sort);
            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movierRequestUrl);
                List<Movie> simpleJsonMovieData = OpenMoviesJsonUtils.getSimpleMoviesStringFromJson(MainActivity.this, jsonMovieResponse);
                return simpleJsonMovieData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<Movie> movies) {
            super.onPostExecute(movies);
            if(movies!=null) {
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new MovieAdapter(movies, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }else {
                textView_error_msg.setVisibility(View.VISIBLE);
            }
        }
    }

}
