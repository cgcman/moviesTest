package com.example.moviefeed.movies;

public class ViewModel {
    private String name;
    private String country;

    public ViewModel(String n, String c){
        this.name = n;
        this.country = c;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
