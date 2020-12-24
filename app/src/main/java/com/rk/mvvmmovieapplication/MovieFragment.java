package com.rk.mvvmmovieapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.databinding.FragmentMovieBinding;
import com.rk.mvvmmovieapplication.viewmodels.PopularMovieViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements ClickListener {

    private List<MovieEntity> articleArrayList = new ArrayList<>();
    private PopularMovieViewModel popularMovieViewmodel;
    private FragmentMovieBinding fragmentMovieBinding;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popularMovieViewmodel = ViewModelProviders.of(this).get(PopularMovieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);
        initRecyclerView();
        initializeAPI();
        return fragmentMovieBinding.getRoot();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2); // you can use getContext() instead of "this"
        fragmentMovieBinding.popularMovieRecyclerview.setLayoutManager(layoutManager);
    }


    private void initializeAPI() {
        popularMovieViewmodel.getMoviesResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(List<MovieEntity> movieEntities) {
                if (!movieEntities.isEmpty()) {
                    setRecyclerViews(movieEntities);
                }
            }
        });
    }


    private void setRecyclerViews(List<MovieEntity> movieEntities) {
        addMovies(movieEntities);
        PopularMovieAdapter popularMovieAdapter = setAdapter(movieEntities);
        setListener(popularMovieAdapter);
    }

    private PopularMovieAdapter setAdapter(List<MovieEntity> movieEntities) {
        PopularMovieAdapter popularMovieAdapter = new PopularMovieAdapter(getContext());
        popularMovieAdapter.setList(movieEntities);
        fragmentMovieBinding.setMyAdapter(popularMovieAdapter);
        return popularMovieAdapter;
    }

    private void setListener(PopularMovieAdapter popularMovieAdapter) {
        popularMovieAdapter.setOnClickListener(this);
    }

    private void addMovies(List<MovieEntity> movieEntities) {
        articleArrayList.clear();
        articleArrayList.addAll(movieEntities);
    }


    @Override
    public void onClick(int position) {
        ((MainActivity) getActivity()).switchMovieDetailsFragment(articleArrayList.get(position));
    }
}
