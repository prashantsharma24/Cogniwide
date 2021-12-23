package com.test.cogniwide.ui.screensecond;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.cogniwide.R;
import com.test.cogniwide.ui.screensecond.adapters.MovieListAdapter;
import com.test.cogniwide.utils.CommonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    Activity mActivity;
    Context mContext;
    RecyclerView rvMovies;
    MovieListAdapter movieListAdapter;

    ArrayList<MovieListResultModel> movieListResultModelArrayList = new ArrayList<>();

    MovieViewModel movieViewModel;
    CommonUtils commonUtils;

    ShimmerFrameLayout movieListShimmerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mActivity = MovieListActivity.this;
        mContext = MovieListActivity.this;

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initialise();

        callAPI();
    }

    public static Intent startIntent(Context mContext) {
        Intent movieListActivity = new Intent(mContext, MovieListActivity.class);
        return movieListActivity;
    }

    private void initialise() {
        commonUtils = new CommonUtils();
        rvMovies = findViewById(R.id.rvMovieList);
        movieListShimmerView = findViewById(R.id.shimmer_view_movie_list);
    }

    private void callAPI() {
        movieListShimmerView.setVisibility(View.VISIBLE);
        movieListShimmerView.startShimmer();
        if (commonUtils.isConnected(mContext)) {
            movieListShimmerView.setVisibility(View.VISIBLE);
            movieListShimmerView.startShimmer();
            movieViewModel.getMovieData().observe(this, movieModel ->
            {
                Gson gson = new GsonBuilder().create();

                String response = gson.toJson(movieModel, MovieListModel.class);

                try {
                    JSONObject jsonResponseObject = new JSONObject(response);
                    JSONArray resultsArray = jsonResponseObject.getJSONArray("results");

                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject resultJsonObject = resultsArray.getJSONObject(i);
                        MovieListResultModel movieListResultModel = new MovieListResultModel();
                        movieListResultModel.setTitle(resultJsonObject.optString("title", ""));
                        movieListResultModel.setPosterPath(resultJsonObject.optString("poster_path", ""));
                        movieListResultModelArrayList.add(movieListResultModel);
                    }

                    movieListShimmerView.stopShimmer();
                    movieListShimmerView.setVisibility(View.GONE);
                    movieListAdapter = new MovieListAdapter(this, movieListResultModelArrayList);
                    rvMovies.setAdapter(movieListAdapter);

                } catch (JSONException e) {
                    movieListShimmerView.stopShimmer();
                    movieListShimmerView.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            });
        } else {
            Toast.makeText(mActivity, "Oops ! No internet connection !", Toast.LENGTH_SHORT).show();
            movieListShimmerView.stopShimmer();
            movieListShimmerView.setVisibility(View.GONE);
        }
    }
}