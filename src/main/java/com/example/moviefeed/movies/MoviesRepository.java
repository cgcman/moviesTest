package com.example.moviefeed.movies;

import android.provider.Settings;

import com.example.moviefeed.http.MovieApiService;
import com.example.moviefeed.http.MoviesExtraInfoApisService;
import com.example.moviefeed.http.apimodel.OmdbApi;
import com.example.moviefeed.http.apimodel.Result;
import com.example.moviefeed.http.apimodel.TopMoviesRated;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoviesExtraInfoApisService moviesExtraInfoApisService;
    private List<String> countries;
    private List<Result> results;
    private long lastTimestamp;
    private static final long CACHE_LIFETIME = 20*1000; //cache durara 20 seg.

    public MoviesRepository(MovieApiService mService, MoviesExtraInfoApisService eServices){
        this.movieApiService = mService;
        this.moviesExtraInfoApisService = eServices;

        this.lastTimestamp = System.currentTimeMillis();

        this.countries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    private boolean isUpdated(){
        return (System.currentTimeMillis() - lastTimestamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }

    @Override
    public Observable<Result> getResultFromNetwork() {
        Observable<TopMoviesRated> topMoviesRatedObservable = movieApiService.getTopMovieRated("d6791a2db32fcae72b9a42ea3bcf2683","1")
        .concatWith(movieApiService.getTopMovieRated("d6791a2db32fcae72b9a42ea3bcf2683","2")
                .concatWith(movieApiService.getTopMovieRated("d6791a2db32fcae72b9a42ea3bcf2683","3")) );
        return topMoviesRatedObservable.concatMap(new Function<TopMoviesRated, ObservableSource<? extends Result>>() {
            @Override
            public Observable<Result> apply(TopMoviesRated topMoviesRated) throws Exception {
                return Observable.fromIterable(topMoviesRated.getResults());
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result) throws Exception {
             results.add(result);
            }
        });
    }

    @Override
    public Observable<Result> getResultFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(results);
        } else{
            lastTimestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountryData() {
        return getCountryFromCache().switchIfEmpty( getCountryFromNetwork() );
    }

    @Override
    public Observable<String> getCountryFromNetwork() {
        return getResultFromNetwork().concatMap(new Function<Result, Observable<OmdbApi>>() {
            @Override
            public Observable<OmdbApi> apply(Result result){
                return moviesExtraInfoApisService.getExtraInfoMovie( result.getTitle());
            }
        }).concatMap(new Function<OmdbApi, ObservableSource<? extends String>>() {
            @Override
            public Observable<String> apply(OmdbApi omdbApi) {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                countries.add(s);
            }
        });
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if(isUpdated()){
            return Observable.fromIterable(countries);
        } else{
            lastTimestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

}
