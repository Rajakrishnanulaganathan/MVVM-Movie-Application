package com.rk.mvvmmovieapplication.data.remote.api;

import com.rk.mvvmmovieapplication.data.remote.model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Call <MoviesResponse> getPopularMovieResults(@Query("api_key") String apikey);
}
