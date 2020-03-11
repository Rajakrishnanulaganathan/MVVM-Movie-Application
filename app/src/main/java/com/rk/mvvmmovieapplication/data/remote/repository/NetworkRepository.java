package com.rk.mvvmmovieapplication.data.remote.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rk.mvvmmovieapplication.data.local.Moviedatabasehelper;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.data.remote.NetworkService;
import com.rk.mvvmmovieapplication.data.remote.api.ApiService;
import com.rk.mvvmmovieapplication.data.remote.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetworkRepository {
    private static NetworkRepository networkRepository;
    private static Moviedatabasehelper moviedatabasehelper;
    private MutableLiveData<MovieEntity> movieEntityMutableLiveData = new MutableLiveData<>();
    private ApiService apiService;
    private MutableLiveData<List<MovieEntity>> moviesResponseMutableLiveData = new MutableLiveData<List<MovieEntity>>();

    private NetworkRepository() {
        apiService = NetworkService.cteateService(ApiService.class);
    }

    public static NetworkRepository networkRepositoryGetinstance(Context context) {
        if (networkRepository == null) {
            networkRepository = new NetworkRepository();
            moviedatabasehelper = new Moviedatabasehelper(context);
        }
        return networkRepository;
    }

    public MutableLiveData<List<MovieEntity>> getMoviesResponseMutableLiveData(final String apikey) {
        final List<MovieEntity> movieEntities = new ArrayList<>();


        apiService.getPopularMovieResults(apikey).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, final Response<MoviesResponse> response) {
                Log.d("movieEntities", String.valueOf(response.body().getResults().size()));
                if (response.isSuccessful()) {
                    for (MovieEntity movieEntity : response.body().getResults()) {
                        movieEntity.setPage(Long.valueOf(response.body().getPage()));
                        if (response.body().getTotalPages() != null)
                            movieEntity.setTotalPages(Long.valueOf(response.body().getTotalPages()));
                        movieEntities.add(movieEntity);
                    }
                    moviedatabasehelper.savetodb(movieEntities);
                    moviesResponseMutableLiveData.setValue(moviedatabasehelper.getAllmoviesfromdb());


                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("fail", t.getLocalizedMessage());
            }
        });

        return moviesResponseMutableLiveData;
    }


    public MutableLiveData<MovieEntity> getMoviebyid(int id) {
        movieEntityMutableLiveData.setValue(moviedatabasehelper.getmovie(id));
        return movieEntityMutableLiveData;
    }


}
