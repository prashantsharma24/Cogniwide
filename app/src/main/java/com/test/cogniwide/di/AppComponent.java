package com.test.cogniwide.di;

import com.test.cogniwide.data.network.RetrofitRepo;
import com.test.cogniwide.ui.screensecond.MovieListActivity;
import com.test.cogniwide.viewmodels.MovieListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MovieListModule.class, AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MovieListViewModel movieListViewModel);

    void inject(RetrofitRepo retrofitRepo);

    void inject(MovieListActivity movieListActivity);
}
