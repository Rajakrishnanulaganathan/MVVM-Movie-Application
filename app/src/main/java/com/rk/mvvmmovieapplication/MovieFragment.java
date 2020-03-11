package com.rk.mvvmmovieapplication;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.data.remote.model.MoviesResponse;
import com.rk.mvvmmovieapplication.databinding.FragmentMovieBinding;
import com.rk.mvvmmovieapplication.utils.Paginationscrolllistner;
import com.rk.mvvmmovieapplication.viewmodels.PopularMovieViewmodel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements ClickListner {

    private PopularMovieAdapter popularMovieAdapter;
    private List<MovieEntity> articleArrayList = new ArrayList<>();
    private PopularMovieViewmodel popularMovieViewmodel;
    private FragmentMovieBinding fragmentMovieBinding;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popularMovieViewmodel = ViewModelProviders.of(this).get(PopularMovieViewmodel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false);

        RecyclerView recyclerView = fragmentMovieBinding.popularMovieRecyclerview; // In xml we have given id rv_movie_list to RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2); // you can use getContext() instead of "this"
        recyclerView.setLayoutManager(layoutManager);
        popularMovieAdapter = new PopularMovieAdapter(getContext());
        intialapicall();
        return fragmentMovieBinding.getRoot();
    }


    private void intialapicall() {
        popularMovieViewmodel.init(getContext());
        popularMovieViewmodel.getMoviesResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(List<MovieEntity> movieEntities) {
                articleArrayList.clear();
                if(!movieEntities.isEmpty()){
                    popularMovieAdapter.setList(movieEntities);}
                  setRecyclerViews();


            }
        });

    }


    private void setRecyclerViews() {
        fragmentMovieBinding.setMyAdapter(popularMovieAdapter);
        popularMovieAdapter.setonclicklistner(this);
    }


    @Override
    public void clickitems(int position) {
        ((MainActivity) getActivity()).moviedetailsfragment(articleArrayList.get(position));
    }
}
