package com.rk.mvvmmovieapplication;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;
import com.rk.mvvmmovieapplication.utils.Constants;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize Toolbar
        initToolBar();
        switchMovieFragment();
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(getApplicationContext().getString(R.string.app_name));
    }

    public void switchMovieFragment() {
        MovieFragment movieFragment = new MovieFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout, movieFragment).commit();

    }

    public void switchMovieDetailsFragment(MovieEntity movieEntity) {
        MovieDetailsFragment moviedetailsfragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.MOVIE_ID, movieEntity.getId());
        moviedetailsfragment.setArguments(bundle);
        showFragment(moviedetailsfragment);
    }

    @Override
    public void onBackPressed() {
        hideBackArrow();
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void showFragment(Fragment fragment) {
        if (fragment instanceof MovieDetailsFragment) {
            showBackArrow();
        } else {
            hideBackArrow();
        }
        fragmentReplace(fragment);

    }

    private void fragmentReplace(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("second").replace(R.id.container_layout, fragment).commit();

    }

    public void showBackArrow() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void hideBackArrow() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }


}
