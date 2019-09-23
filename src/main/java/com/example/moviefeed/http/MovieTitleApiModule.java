package com.example.moviefeed.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class MovieTitleApiModule {
    public final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public final String API_KEY = "d6791a2db32fcae72b9a42ea3bcf2683";

    @Provides
    public OkHttpClient provideClient(){
        return null;
    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client){
        return null;
    }

    @Provides
    public MovieApiService provideApiService(){
        return provideRetrofit(BASE_URL, provideClient()).create(MovieApiService.class);
    }
}
