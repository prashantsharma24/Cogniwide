package com.test.cogniwide.di;

import android.app.Application;

import com.test.cogniwide.BuildConfig;

import javax.inject.Inject;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Inject
    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.API_URL)).
                        movieListModule(new MovieListModule(this)).build();
    }

    public AppComponent getNetComponent() {
        return mAppComponent;
    }
}
