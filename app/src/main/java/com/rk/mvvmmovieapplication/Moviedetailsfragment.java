package com.rk.mvvmmovieapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.databinding.ActivityMovieDetailsBinding;
import com.rk.mvvmmovieapplication.utils.Constants;
import com.rk.mvvmmovieapplication.viewmodels.PopularMovieGetViewmodel;
import com.squareup.picasso.Picasso;

public class Moviedetailsfragment extends Fragment {
     ActivityMovieDetailsBinding activityMovieDetailsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMovieDetailsBinding= DataBindingUtil.inflate(inflater,R.layout.activity_movie_details,container,false);
        View view=activityMovieDetailsBinding.getRoot();
        Bundle bundle=this.getArguments();
        MovieEntity movieEntity= (MovieEntity) bundle.getSerializable("movie");

        PopularMovieGetViewmodel popularMovieGetViewmodel= ViewModelProviders.of(this).get(PopularMovieGetViewmodel.class);
        popularMovieGetViewmodel.init(getContext(),movieEntity.getId());
        popularMovieGetViewmodel.getMovie().observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(MovieEntity movieEntity) {
                activityMovieDetailsBinding.setMovie(movieEntity);

                addMovieDetails(movieEntity);

            }
        });
        return view;
    }



    private void addMovieDetails(MovieEntity movieEntity) {
        Picasso.get().load(Constants.IMAGE_ENDPOINT_PREFIX+movieEntity.getPosterPath()).into(activityMovieDetailsBinding.imageViewCover);

    }
}
