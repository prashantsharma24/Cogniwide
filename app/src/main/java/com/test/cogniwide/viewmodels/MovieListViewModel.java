package com.test.cogniwide.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.test.cogniwide.data.model.MovieListDetailResponseModel;
import com.test.cogniwide.data.network.RetrofitRepo;
import com.test.cogniwide.di.AppComponent;
import com.test.cogniwide.di.MyApplication;

import java.util.List;

import javax.inject.Inject;

public class MovieListViewModel extends AndroidViewModel {

    @Inject
    public RetrofitRepo mRetrofitRepository;
    private AppComponent mAppComponent;

    public MovieListViewModel(@NonNull Application mApplication) {
        super(mApplication);
        ((MyApplication) mApplication).getNetComponent().inject(this);
    }

    public MutableLiveData<List<MovieListDetailResponseModel>> getMovieListRepository() {
        return mRetrofitRepository.getMoviesList();
    }

}
