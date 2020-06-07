package com.example.organizationalapp.NewsPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.organizationalapp.ForntPart.BaseContext;
import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class SecondPage extends BaseContext {
    Navigation nv;
    BottomNavigationView navigation;
    NavigationView navigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        nv = new Navigation();
        findView();
        setToolBar();
        setNavigation();
        //  setTopNavigation();
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerNewsAdapt viewPagerNewsAdapt = new ViewPagerNewsAdapt(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerNewsAdapt);
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "همه");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "خبر");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "اطلاعیه");

    }

    private void setNavigation() {
        nv.setNavigation(navigationView, this);
        navigation.setSelectedItemId(R.id.news_part);
        nv.setButtomNavigation(navigation, this);


    }

    private void findView() {
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);


    }

    public void setToolBar() {
        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setLogo(R.drawable.homewhite);
        toolbars.setNavigationIcon(R.drawable.menu);
        getSupportActionBar().setIcon(R.drawable.logofitwhite);

    }

}
