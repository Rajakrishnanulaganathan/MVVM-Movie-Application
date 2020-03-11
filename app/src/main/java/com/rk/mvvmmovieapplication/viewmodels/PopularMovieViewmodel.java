package com.rk.mvvmmovieapplication.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.data.remote.repository.NetworkRepository;
import com.rk.mvvmmovieapplication.utils.Constants;

import java.util.List;

public class PopularMovieViewmodel extends ViewModel {
    private MutableLiveData<List<MovieEntity>> moviesResponseMutableLiveData;
    private NetworkRepository networkRepository;

    public void init(Context context){
        if(moviesResponseMutableLiveData==null){
            networkRepository= NetworkRepository.networkRepositoryGetinstance(context);
            moviesResponseMutableLiveData= networkRepository.getMoviesResponseMutableLiveData(Constants.API_KEY);
        }
    }

    public MutableLiveData<List<MovieEntity>> getMoviesResponseMutableLiveData(){
        return moviesResponseMutableLiveData;
    }




}
