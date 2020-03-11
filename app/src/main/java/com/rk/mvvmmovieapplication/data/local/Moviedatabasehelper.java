package com.rk.mvvmmovieapplication.data.local;

import android.content.Context;
import android.util.Log;

import com.rk.mvvmmovieapplication.data.local.dao.MovieDao;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;

import java.util.List;

public class Moviedatabasehelper {

    private MovieDao movieDao;
    private Appdatabase appdatabase;

    public Moviedatabasehelper(Context context) {
        appdatabase=Appdatabase.getDatabaseInstance(context);
        movieDao = appdatabase.movieDao();
    }

    public List<MovieEntity>getAllmoviesfromdb() {
        return movieDao.getallmovies();}


    public void savetodb(List<MovieEntity> movies) {
        if(!movies.isEmpty()){
        movieDao.insertmovies(movies);
        }
    }

    public MovieEntity getmovie(int id) {
        return movieDao.getmovie(id) ;
    }
}
