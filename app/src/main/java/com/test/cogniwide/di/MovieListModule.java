package com.test.cogniwide.di;

import android.content.Context;

import com.test.cogniwide.data.network.RetrofitRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    private final Context mContext;

    public MovieListModule (Context mContext) {
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    public RetrofitRepo provideContext() {
        return new RetrofitRepo(mContext);
    }
}
