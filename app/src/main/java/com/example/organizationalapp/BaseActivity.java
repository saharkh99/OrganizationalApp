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

import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.NewsPart.NewsActivity;
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
        if(NewsActivity.code==5 || NewsActivity.code==4)
        this.navigation.setSelectedItemId(R.id.home_part);
        if(NewsActivity.code==3)
            this.navigation.setSelectedItemId(R.id.service_part);
        setButtomNavigation(navigation);
        if (NewsActivity.code==5) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
            Navigation.newsClicked = false;
            Navigation.homeClicked = true;
            transaction.add(R.id.fragment_container, homeFragment, "HomeFragment").commit();
        }
        else if(NewsActivity.code==1){
//            NewsFragment newsFragment = NewsFragment.newInstance();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
//            transaction.add(R.id.fragment_container, newsFragment, "news").commit();
            if (getIntent().putExtra("fra", "x") != null) {
             //   this.navigation.setSelectedItemId(R.id.news_part);
                Fragment fragment = new NewsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                transaction.add(R.id.fragment_container, fragment, "news").commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = true;
                Navigation.homeClicked = false;
            }
        }
        else {

            String frag = getIntent().getExtras().getString("frag");
            Fragment fragment;
            FragmentManager fragmentManager=getSupportFragmentManager();
           // if(frag!=null) {
            //    switch (frag) {

                  //  case "news":
            if(NewsActivity.code==2) {
                fragment = new NewsFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                transaction.replace(R.id.drawer, fragment);
                transaction.commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = true;
                Navigation.homeClicked = false;
            }
                   // case "service":
            if(NewsActivity.code==3) {
                Log.d("omad", "are");
                Fragment fragment1 = new ServiceFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                transaction1.add(R.id.fragment_container, fragment1);
                transaction1.commit();
                Navigation.serviceClicked = true;
                Navigation.newsClicked = false;
                Navigation.homeClicked = false;
            }
                //    case "home":
            if(NewsActivity.code==4) {
                fragment = new HomeFragment();
                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                if (Navigation.newsClicked) {
                    transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
                } else {
                    transaction2.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
                }
                transaction2.add(R.id.fragment_container, fragment);
                transaction2.commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = false;
                Navigation.homeClicked = true;
            }


        }




    }
    @Override
    public void onResume(){
        super.onResume();

        Intent intent = getIntent();


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