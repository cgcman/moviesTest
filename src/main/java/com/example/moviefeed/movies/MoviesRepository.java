package com.example.moviefeed.movies;

import com.example.moviefeed.http.MovieApiService;
import com.example.moviefeed.http.MoviesExtraInfoApisService;
import com.example.moviefeed.http.apimodel.Result;

import io.reactivex.Observable;

public class MoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoviesExtraInfoApisService moviesExtraInfoApisService;

    public MoviesRepository(MovieApiService mService, MoviesExtraInfoApisService eServices){
        this.movieApiService = mService;
        this.moviesExtraInfoApisService = eServices;
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }

    @Override
    public Observable<String> getCountryData() {
        return null;
    }
}
