package com.example.moviefeed.movies;

import com.example.moviefeed.http.apimodel.Result;

import io.reactivex.Observable;

public interface Repository {
    Observable<Result> getResultData();
    Observable<String> getCountryData();
}
