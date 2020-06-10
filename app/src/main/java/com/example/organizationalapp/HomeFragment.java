package com.example.organizationalapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {

    View view;
    MaterialButton news, service;
    BottomNavigationView navigation;
    NavigationView navigationView;
    TextView welcome;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);

        findView();
        findView();
       setNavigation();
        setToolBar();
        welcome.setText(User.getName() + "   عزیز" + "\n" + "به برنامه جامع خوش آمدید");
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchNews();
                navigation.setSelectedItemId(R.id.news_part);

            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               switchService();
                navigation.setSelectedItemId(R.id.service_part);

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
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.service_part:
                        item.setTitle("");
                        switchService();
                        break;
                    case R.id.news_part:
                        item.setTitle("");
                        switchNews();

                }
                return false;
            }
        });
    }
    public void switchService(){
        ServiceFragment fragment2 = new ServiceFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.drawer, fragment2);
        fragmentTransaction.commit();
    }
    public void switchNews(){
        NewsFragment fragment = new NewsFragment();
        FragmentManager fragmentManager1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.replace(R.id.drawer, fragment);
        fragmentTransaction1.commit();
    }
}