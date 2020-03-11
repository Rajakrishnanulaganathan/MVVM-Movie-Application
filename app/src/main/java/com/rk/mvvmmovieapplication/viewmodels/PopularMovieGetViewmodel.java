package com.rk.mvvmmovieapplication.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.data.remote.model.MoviesResponse;
import com.rk.mvvmmovieapplication.data.remote.repository.NetworkRepository;
import com.rk.mvvmmovieapplication.utils.Constants;

public class PopularMovieGetViewmodel extends ViewModel {
    private MutableLiveData<MovieEntity> moviesResponseMutableLiveData;
    protected NetworkRepository networkRepository;



    public void init(Context context,int id){
        if(moviesResponseMutableLiveData==null){
            networkRepository= NetworkRepository.networkRepositoryGetinstance(context);
            moviesResponseMutableLiveData= networkRepository.getMoviebyid(id);
        }
    }

    public LiveData<MovieEntity> getMovie(){
        return moviesResponseMutableLiveData;
    }

}
