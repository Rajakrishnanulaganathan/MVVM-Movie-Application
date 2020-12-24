package com.rk.mvvmmovieapplication.data.local;

import android.content.Context;

import com.rk.mvvmmovieapplication.data.local.dao.MovieDao;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;

import java.util.List;

public class MovieDataBaseHelper {

    private MovieDao movieDao;
    private AppDataBase appdatabase;

    public MovieDataBaseHelper(Context context) {
        appdatabase = AppDataBase.getDatabaseInstance(context);
        movieDao = appdatabase.movieDao();
    }

    //Get all movies from DB
    public List<MovieEntity> getAllMoviesFromDB() {
        return movieDao.getAllMovies();
    }

    //Save all movies from DB
    public void saveMoviesIntoDB(List<MovieEntity> movies) {
        if (!movies.isEmpty()) {
            movieDao.insertMovies(movies);
        }
    }

    //Get movie form DB
    public MovieEntity getMoviesFromDB(int id) {
        return movieDao.getMovie(id);
    }

    //Delete all movies from DB
    public void deleteAllMoviesDB() {
        movieDao.deleteAllMovies();
    }
}
