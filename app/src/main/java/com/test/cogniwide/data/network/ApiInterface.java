package com.test.cogniwide.data.network;

import com.test.cogniwide.data.model.MovieListResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("popular")
    Call<MovieListResponseModel> getTopRatedMovies(@Query("api_key") String apiKey);

}
