package com.test.cogniwide.data.network;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.test.cogniwide.BuildConfig;
import com.test.cogniwide.data.model.MovieListDetailResponseModel;
import com.test.cogniwide.data.model.MovieListResponseModel;
import com.test.cogniwide.di.MyApplication;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRepo {

    private ApiInterface mApiInterface;
    Context context;
    @Inject
    Retrofit mRetrofit;

    @Inject
    public RetrofitRepo(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<MovieListDetailResponseModel>> getMoviesList() {
        ((MyApplication) context).getNetComponent().inject(this);
        MutableLiveData<List<MovieListDetailResponseModel>> poListMutableLiveData = new MutableLiveData<>();
        mApiInterface = mRetrofit.create(ApiInterface.class);
        Call<MovieListResponseModel> call = mApiInterface.getTopRatedMovies(BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieListResponseModel>() {
            @Override
            public void onResponse(Call<MovieListResponseModel> call, Response<MovieListResponseModel> response) {
                MovieListResponseModel dataList = response.body();
                poListMutableLiveData.setValue(dataList.getResults());
            }

            @Override
            public void onFailure(Call<MovieListResponseModel> call, Throwable t) {
                poListMutableLiveData.setValue(null);
            }
        });
        return poListMutableLiveData;
    }

}
