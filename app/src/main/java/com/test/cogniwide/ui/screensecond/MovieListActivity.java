package com.test.cogniwide.ui.screensecond;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.cogniwide.R;
import com.test.cogniwide.data.model.MovieListDetailResponseModel;
import com.test.cogniwide.databinding.ActivityMovieListBinding;
import com.test.cogniwide.ui.screensecond.adapters.MovieListAdapter;
import com.test.cogniwide.utils.CommonUtils;
import com.test.cogniwide.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    ActivityMovieListBinding mMovieListBinding;
    MovieListViewModel movieListViewModel;
    CommonUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonUtils = new CommonUtils();

        mMovieListBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        mMovieListBinding.shimmerViewMovieList.setVisibility(View.VISIBLE);
        mMovieListBinding.shimmerViewMovieList.startShimmer();
        if (commonUtils.isConnected(this)) {
            mMovieListBinding.shimmerViewMovieList.setVisibility(View.VISIBLE);
            mMovieListBinding.shimmerViewMovieList.startShimmer();
            movieListViewModel.getMovieListRepository().observe(this, new Observer<List<MovieListDetailResponseModel>>() {
                @Override
                public void onChanged(List<MovieListDetailResponseModel> movieList) {
                    MovieListAdapter movieListAdapter = new MovieListAdapter(getApplicationContext(), movieList);
                    mMovieListBinding.rvMovieList.setAdapter(movieListAdapter);
                    mMovieListBinding.shimmerViewMovieList.stopShimmer();
                    mMovieListBinding.shimmerViewMovieList.setVisibility(View.GONE);

                }
            });
        } else {
            Toast.makeText(this, "Oops ! No internet connection !", Toast.LENGTH_SHORT).show();
            mMovieListBinding.shimmerViewMovieList.stopShimmer();
            mMovieListBinding.shimmerViewMovieList.setVisibility(View.GONE);
        }

    }
}