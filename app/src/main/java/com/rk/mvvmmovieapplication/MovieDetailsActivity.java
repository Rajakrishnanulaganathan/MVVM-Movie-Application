package com.rk.mvvmmovieapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.viewmodels.PopularMovieGetViewmodel;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle bundle=getIntent().getExtras();
        int id=bundle.getInt("id");

        PopularMovieGetViewmodel popularMovieGetViewmodel= ViewModelProviders.of(this).get(PopularMovieGetViewmodel.class);
        popularMovieGetViewmodel.init(getApplicationContext(),id);
        popularMovieGetViewmodel.getMovie().observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(MovieEntity movieEntity) {

            }
        });
    }
}
