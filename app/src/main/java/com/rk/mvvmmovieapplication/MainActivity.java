package com.rk.mvvmmovieapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.rk.mvvmmovieapplication.data.local.entity.MovieEntity;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    MovieFragment movieFragment ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Movies");
        movieFragment = new MovieFragment();

        moviefragment();


    }

    public void moviefragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout, movieFragment).commit();

    }

        public void moviedetailsfragment(MovieEntity movieEntity) {
        Moviedetailsfragment moviedetailsfragment = new Moviedetailsfragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movieEntity);
        moviedetailsfragment.setArguments(bundle);
        showFragment(moviedetailsfragment);
    }

    @Override
    public void onBackPressed() {
        hidebackarrow();
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
    }
        else {
            super.onBackPressed();
        }
    }

    private void showFragment(Fragment fragment) {
        if (fragment instanceof Moviedetailsfragment) {
            showbackarrow();
        }
        else {
            hidebackarrow();
        }
        makeaddandreplacethefragment(fragment);

    }

    private void makeaddandreplacethefragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("second").replace(R.id.container_layout, fragment).commit();

    }

    public void showbackarrow(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void hidebackarrow(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }


}
