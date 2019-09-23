package com.example.moviefeed.di;

import android.app.Application;

import com.example.moviefeed.http.MovieExtraInfoApiModule;
import com.example.moviefeed.http.MovieTitleApiModule;
import com.example.moviefeed.movies.MoviesModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .moviesModule(new MoviesModule())
                .movieExtraInfoApiModule(new MovieExtraInfoApiModule())
                .movieTitleApiModule(new MovieTitleApiModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
