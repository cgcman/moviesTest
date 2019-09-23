package com.example.moviefeed.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MovieExtraInfoApiModule {
    public final String BASE_URL = "http://www.omdbapi.com/";

    @Provides
    public OkHttpClient provideClient(){
        return null;
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client){
        return null;
    }

    @Provides
    public MoviesExtraInfoApisService provideApiService(){
        return provideRetrofit(BASE_URL, provideClient()).create(MoviesExtraInfoApisService.class);
    }

}
