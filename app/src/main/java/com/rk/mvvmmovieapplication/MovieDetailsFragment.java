package com.rk.mvvmmovieapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.databinding.ActivityMovieDetailsBinding;
import com.rk.mvvmmovieapplication.utils.Constants;
import com.rk.mvvmmovieapplication.viewmodels.PopularMovieViewModel;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    ActivityMovieDetailsBinding activityMovieDetailsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMovieDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.activity_movie_details, container, false);
        Bundle bundle = this.getArguments();
        PopularMovieViewModel popularMovieGetViewmodel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);
        popularMovieGetViewmodel.getMovie(bundle.getInt(Constants.MOVIE_ID)).observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(MovieEntity movieEntity) {
                activityMovieDetailsBinding.setMovie(movieEntity);
                addMovieDetails(movieEntity);
            }
        });
        return activityMovieDetailsBinding.getRoot();
    }


    private void addMovieDetails(MovieEntity movieEntity) {
        if (movieEntity.getPosterPath() != null)
            Picasso.get().load(Constants.IMAGE_ENDPOINT_PREFIX + movieEntity.getPosterPath()).into(activityMovieDetailsBinding.imageViewCover);
    }
}
