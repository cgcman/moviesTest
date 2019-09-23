package com.example.moviefeed.http;

import com.example.moviefeed.http.apimodel.OmdbApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesExtraInfoApisService {

    @GET("/")
    Observable<OmdbApi> getExtraInfoMovie(@Query("t") String title);
}
