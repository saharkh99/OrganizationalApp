package com.example.organizationalapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.NewsPart.NewsActivity;
import com.example.organizationalapp.NewsPart.NewsFragment;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    static BottomNavigationView navigation;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    Navigation nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        findView();
        nv = new Navigation();
        setToolBar();
        Fragment fragments = null;
        FragmentManager fragmentManagers = getSupportFragmentManager();
        nv.setNavigation(navigationView, fragments, fragmentManagers, mDrawerLayout);
        nv.setButtomNavigation(navigation, fragments, fragmentManagers);

        if (NewsActivity.code == 5 || NewsActivity.code == 4)
            this.navigation.setSelectedItemId(R.id.home_part);
        if (NewsActivity.code == 3)
            this.navigation.setSelectedItemId(R.id.service_part);
        if (NewsActivity.code == 5) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right,R.animator.enter_from_left, R.animator.exit_to_right);
            Navigation.newsClicked = false;
            Navigation.homeClicked = true;
            transaction.add(R.id.fragment_container, homeFragment, "HomeFragment").commit();
        } else if (NewsActivity.code == 1) {
            if (getIntent().putExtra("fra", "x") != null) {
                //   this.navigation.setSelectedItemId(R.id.news_part);
                Fragment fragment = new NewsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
                transaction.add(R.id.fragment_container, fragment, "news").commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = true;
                Navigation.homeClicked = false;
            }
        } else {
            Fragment fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (NewsActivity.code == 2) {
                fragment = new NewsFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
                transaction.replace(R.id.drawer, fragment);
                transaction.commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = true;
                Navigation.homeClicked = false;
            }

            if (NewsActivity.code == 3) {
                Log.d("omad", "are");
                Fragment fragment1 = new ServiceFragment();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
                transaction1.add(R.id.fragment_container, fragment1);
                transaction1.commit();
                Navigation.serviceClicked = true;
                Navigation.newsClicked = false;
                Navigation.homeClicked = false;
            }
            //    case "home":
            if (NewsActivity.code == 4) {
                fragment = new HomeFragment();
                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
              //  if (Navigation.newsClicked) {
              //      transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
              //  } else {
                    transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
              //  }
                transaction2.add(R.id.fragment_container, fragment);
                transaction2.commit();
                Navigation.serviceClicked = false;
                Navigation.newsClicked = false;
                Navigation.homeClicked = true;
            }


        }


    }

    public void findView() {
        frameLayout = findViewById(R.id.fragment_container);
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = getIntent();


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    public void setToolBar() {
        Toolbar toolbars = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        setSupportActionBar(toolbars);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setNavigationIcon(R.drawable.menu);
    }

}