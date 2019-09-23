package com.example.moviefeed.http;

import com.example.moviefeed.http.apimodel.TopMoviesRated;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("top_rated")
    Observable<TopMoviesRated> getTopMovieRated(@Query("api_key") String api_key,
                                                @Query("page") String page);
}
