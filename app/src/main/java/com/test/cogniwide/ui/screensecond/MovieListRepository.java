package com.test.cogniwide.ui.screensecond;

import androidx.lifecycle.MutableLiveData;

import com.test.cogniwide.BuildConfig;
import com.test.cogniwide.data.api.ApiFactory;
import com.test.cogniwide.data.api.ApiInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieListRepository {

    private static final String TAG = "MovieListRepository";

    private final ApiInterface apiInterface;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MovieListRepository() {
        this.apiInterface = ApiFactory.getClient(BuildConfig.API_URL).create(ApiInterface.class);
    }

    public MutableLiveData<MovieListModel> getMovieList() {
        MutableLiveData<MovieListModel> movieListModelMutableLiveData = new MutableLiveData<>();

        compositeDisposable.add(apiInterface.getMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieListModel ->
                {
                    if (movieListModel != null) {
                        movieListModelMutableLiveData.setValue(movieListModel);
                    }
                }));

        return movieListModelMutableLiveData;

    }
}
