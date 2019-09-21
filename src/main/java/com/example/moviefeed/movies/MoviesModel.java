package com.example.moviefeed.movies;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MoviesModel implements MoviesMVP.Model {

    private MovieRepository movieRepository;

    public MoviesModel(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(movieRepository.getResultData(), movieRepository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String country) {
                return new ViewModel(result.toString(), country);
            }
        });
    }
}
