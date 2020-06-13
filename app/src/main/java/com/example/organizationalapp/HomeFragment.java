package com.example.organizationalapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {

    View view;
    FragmentActivity fragmentActivity;
    MaterialButton news, service;
    BottomNavigationView navigation;
    NavigationView navigationView;
    static TextView welcome;
    Navigation nv;
    FragmentTransaction fragmentTransaction;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        fragmentActivity=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);

        findView();
        welcome.setText(User.getName() + "   عزیز" + "\n" + "به برنامه جامع خوش آمدید");
        setToolBar();
        nv=new Navigation();
        setNavigation();


        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.setSelectedItemId(R.id.news_part);
                nv.changingNewsFragment(fragmentActivity);

            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.setSelectedItemId(R.id.service_part);
                nv.changingServiceFragment(fragmentActivity);
            }
        });

        return view;
    }
    private void findView() {
        news = view.findViewById(R.id.news_btn);
        service = view.findViewById(R.id.service_btn);
        welcome = view.findViewById(R.id.welcome);
        navigation = view.findViewById(R.id.navigation);
        navigationView = view.findViewById(R.id.navigation_view);
    }

    public void setToolBar() {
        Toolbar toolbars =view.findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = view.findViewById(R.id.drawer);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbars);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setNavigationIcon(R.drawable.menu);
    }

    private void setNavigation() {
        navigation.setSelectedItemId(R.id.home_part);
        nv.setNavigation(navigationView, fragmentActivity);
        nv.setButtomNavigation(navigation, fragmentActivity);
    }

    @Override
    public void onPause() {
        welcome.setVisibility(View.INVISIBLE);
        super.onPause();

    }

}