package com.rk.mvvmmovieapplication;

import android.app.Application;

import androidx.lifecycle.ViewModelProviders;

import com.rk.mvvmmovieapplication.viewmodels.PopularMovieViewmodel;

public class MovieApplication extends Application {

    private PopularMovieViewmodel popularMovieViewmodel;

    @Override
    public void onCreate() {
        super.onCreate();
       // popularMovieViewmodel = ViewModelProviders.of(getAc).get(PopularMovieViewmodel.class);

    }


}
