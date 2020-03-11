package com.rk.mvvmmovieapplication.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rk.mvvmmovieapplication.data.local.converter.Integertypeconverter;
import com.rk.mvvmmovieapplication.data.local.dao.MovieDao;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;

@Database(entities = MovieEntity.class,version = 2,exportSchema = false)
@TypeConverters(Integertypeconverter.class)
public abstract class Appdatabase extends RoomDatabase {

    public static Appdatabase mAppDatabaseInstance;

    public static synchronized Appdatabase  getDatabaseInstance(Context context){
        if(mAppDatabaseInstance==null){
            mAppDatabaseInstance= Room.databaseBuilder(context,Appdatabase.class,"user.db").allowMainThreadQueries().build();
        }
        return mAppDatabaseInstance;
    }

    public abstract MovieDao movieDao();

}
