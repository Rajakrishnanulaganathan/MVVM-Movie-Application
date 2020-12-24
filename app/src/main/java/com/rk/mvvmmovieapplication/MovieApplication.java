package com.rk.mvvmmovieapplication;

import android.app.Application;

import com.rk.mvvmmovieapplication.data.remote.repository.NetworkRepository;

public class MovieApplication extends Application {

    private static NetworkRepository networkRepository;

    public static NetworkRepository getNetworkRepository() {
        return networkRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        networkRepository = NetworkRepository.networkRepositoryGetInstance(getApplicationContext());
    }

}
