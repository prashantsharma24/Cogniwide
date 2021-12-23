package com.test.cogniwide.data.api;

import com.test.cogniwide.ui.screensecond.MovieListModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("3/movie/popular?api_key=434d1d4ec64f574aed3d6f31bc984c2f")
    Observable<MovieListModel> getMovieData();

}
