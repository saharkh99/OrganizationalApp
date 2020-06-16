package com.example.organizationalapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.organizationalapp.NewsPart.NewsFragment;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    static BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        frameLayout = findViewById(R.id.fragment_container);
        navigation = findViewById(R.id.navigation);
        this.navigation.setSelectedItemId(R.id.home_part);
        setButtomNavigation(navigation);
        if (getIntent().getFlags() == Intent.FLAG_ACTIVITY_NEW_TASK) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
            Navigation.newsClicked = false;
            Navigation.homeClicked = true;
            transaction.add(R.id.fragment_container, homeFragment, "HomeFragment").commit();
        } else {
//            NewsFragment newsFragment = NewsFragment.newInstance();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
//            transaction.add(R.id.fragment_container, newsFragment, "news").commit();
           Fragment fragment = new NewsFragment();
           FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
            transaction.add(R.id.fragment_container, fragment, "news").commit();
            Navigation.serviceClicked = false;
            Navigation.newsClicked = true;
            Navigation.homeClicked = false;
        }

    }
    @Override
    public void onResume(){
        super.onResume();

        Intent intent = getIntent();

        String frag = intent.getExtras().getString("frag");
        Fragment fragment;
        FragmentManager fragmentManager=getSupportFragmentManager();
        if(frag!=null) {
            switch (frag) {

                case "news":
                    fragment = new NewsFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                    transaction.replace(R.id.drawer, fragment);
                    transaction.commit();
                    Navigation.serviceClicked = false;
                    Navigation.newsClicked = true;
                    Navigation.homeClicked = false;
                case "service":
                    fragment = new ServiceFragment();
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                    transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                    transaction1.replace(R.id.drawer, fragment);
                    transaction1.commit();
                    Navigation.serviceClicked = true;
                    Navigation.newsClicked = false;
                    Navigation.homeClicked = false;
                case "home":

                    fragment = new HomeFragment();
                    FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                    if (Navigation.newsClicked) {
                        transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                    } else {
                        transaction2.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                    }
                    transaction2.replace(R.id.drawer, fragment);
                    transaction2.commit();
                    Navigation.serviceClicked = false;
                    Navigation.newsClicked = false;
                    Navigation.homeClicked = true;

            }
        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    public void setButtomNavigation(BottomNavigationView navigation) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            FragmentManager fragmentManager=getSupportFragmentManager();
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        if(!Navigation.newsClicked) {
                            fragment = new NewsFragment();
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                            transaction.replace(R.id.drawer, fragment);
                            transaction.commit();
                            item.setChecked(true);
                            Navigation.serviceClicked = false;
                            Navigation.newsClicked = true;
                            Navigation.homeClicked = false;
                        }
                        break;
                    case R.id.service_part:
                        if(!Navigation.serviceClicked) {
                            fragment = new ServiceFragment();
                            item.setChecked(true);
                            FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                            transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                            transaction1.replace(R.id.drawer, fragment);
                            transaction1.commit();
                            Navigation.serviceClicked = true;
                            Navigation.newsClicked = false;
                            Navigation.homeClicked = false;
                        }
                        break;
                    case R.id.home_part:
                        if(!Navigation.homeClicked) {
                            fragment = new HomeFragment();
                            item.setChecked(true);
                            FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                            if (Navigation.newsClicked) {
                                transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                            } else {
                                transaction2.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                            }                            transaction2.replace(R.id.drawer, fragment);
                            transaction2.commit();
                            Navigation.serviceClicked = false;
                            Navigation.newsClicked = false;
                            Navigation.homeClicked = true;
                        }

                        break;
                }
                return false;
            }
        });
    }

}
