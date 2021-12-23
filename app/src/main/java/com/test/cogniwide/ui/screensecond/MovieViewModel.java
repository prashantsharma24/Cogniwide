package com.test.cogniwide.ui.screensecond;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class MovieViewModel extends ViewModel {

    private final MovieListRepository movieRepo;

    public MovieViewModel() {
        movieRepo = new MovieListRepository();
    }

    public LiveData<MovieListModel> getMovieData() {
        return movieRepo.getMovieList();
    }
}
