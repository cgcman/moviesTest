package com.example.moviefeed.di;

import com.example.moviefeed.MainActivity;
import com.example.moviefeed.http.MovieExtraInfoApiModule;
import com.example.moviefeed.http.MovieTitleApiModule;
import com.example.moviefeed.movies.MoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MoviesModule.class, MovieTitleApiModule.class, MovieExtraInfoApiModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
