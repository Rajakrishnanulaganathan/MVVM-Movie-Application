package com.rk.mvvmmovieapplication.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rk.mvvmmovieapplication.data.local.converter.Integertypeconverter;
import com.rk.mvvmmovieapplication.data.local.dao.MovieDao;
import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;

@Database(entities = MovieEntity.class, version = 2, exportSchema = false)
@TypeConverters(Integertypeconverter.class)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase mAppDatabaseInstance;

    public static synchronized AppDataBase getDatabaseInstance(Context context) {
        if (mAppDatabaseInstance == null) {
            mAppDatabaseInstance = Room.databaseBuilder(context, AppDataBase.class, "user.db").allowMainThreadQueries().build();
        }
        return mAppDatabaseInstance;
    }

    public abstract MovieDao movieDao();

}
