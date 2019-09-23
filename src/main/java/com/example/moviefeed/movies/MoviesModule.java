package com.example.moviefeed.movies;

import com.example.moviefeed.http.MovieApiService;
import com.example.moviefeed.http.MoviesExtraInfoApisService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviesMVP.Presenter provideMoviesPresenter(MoviesMVP.Model moviesModel){
        return new MoviesPresenter(moviesModel);
    };

    @Provides
    public MoviesMVP.Model provideMoviesModel(Repository repository){
        return new MoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideMovieRepository(MovieApiService movieApiService, MoviesExtraInfoApisService moviesExtraInfoApisService){
        return new MoviesRepository(movieApiService, moviesExtraInfoApisService);
    }
}
