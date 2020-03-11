package com.rk.mvvmmovieapplication.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rk.mvvmmovieapplication.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }




}
