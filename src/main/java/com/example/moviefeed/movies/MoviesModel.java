package com.example.moviefeed.movies;

import com.example.moviefeed.http.apimodel.Result;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MoviesModel implements MoviesMVP.Model {

    private Repository repository;

    public MoviesModel(Repository repository){
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(repository.getResultData(), repository.getCountryData(), new BiFunction<Result, String, ViewModel>() {
            @Override
            public ViewModel apply(Result result, String country) {
                return new ViewModel(result.toString(), country);
            }
        });
    }
}
