package com.rk.mvvmmovieapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rk.mvvmmovieapplication.MovieApplication;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.utils.Constants;

import java.util.List;

public class PopularMovieViewModel extends ViewModel {

    public MutableLiveData<List<MovieEntity>> getMoviesResponseMutableLiveData() {
        return MovieApplication.getNetworkRepository().getMoviesResponseMutableLiveData(Constants.API_KEY);
    }

    public LiveData<MovieEntity> getMovie(int id) {
        return MovieApplication.getNetworkRepository().getMoviebyid(id);
    }
}
