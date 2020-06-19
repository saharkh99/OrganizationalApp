package com.example.organizationalapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

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
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        findView();
        nv = new Navigation();
        setToolBar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        nv.setNavigation(navigationView, fragmentManager, mDrawerLayout);
        nv.setButtomNavigation(navigation, fragmentManager);

        if (NewsActivity.code == 5 || NewsActivity.code == 4)
            this.navigation.setSelectedItemId(R.id.home_part);
        if (NewsActivity.code == 3)
            this.navigation.setSelectedItemId(R.id.service_part);
        if (NewsActivity.code == 5) {
            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
            Navigation.newsClicked = false;
            Navigation.homeClicked = true;
            transaction.add(R.id.fragment_container, HomeFragment.newInstance(), "HomeFragment").commit();
        } else if (NewsActivity.code == 1) {
            if (getIntent().putExtra("fra", "x") != null) {
                transactionFromLeft(transaction);
                transaction.add(R.id.fragment_container, NewsFragment.newInstance(), "news").commit();
                Navigation.setNavigationItemSelect(false, false, true);

            }
        } else {
            if (NewsActivity.code == 2) {
                transactionFromLeft(transaction);
                //////////////////////////////////////////////////////////
                transaction.replace(R.id.drawer, NewsFragment.newInstance());
                Navigation.setNavigationItemSelect(false, false, true);

            }

            if (NewsActivity.code == 3) {
                transactionFromRight(transaction);
                transaction.add(R.id.fragment_container, ServiceFragment.newInstance());
                Navigation.setNavigationItemSelect(false, true, false);

            }
            if (NewsActivity.code == 4) {
                transactionFromRight(transaction);
                transaction.add(R.id.fragment_container, HomeFragment.newInstance());
                Navigation.setNavigationItemSelect(true, false, false);
            }

            transaction.commit();

        }


    }

    public void findView() {
        frameLayout = findViewById(R.id.fragment_container);
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
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

    public void transactionFromLeft(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
    }

    public void transactionFromRight(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);

    }
}