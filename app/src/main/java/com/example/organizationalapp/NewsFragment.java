package com.example.organizationalapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.organizationalapp.NewsPart.NewsFragmentList;
import com.example.organizationalapp.NewsPart.ViewPagerNewsAdapt;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NewsFragment extends Fragment {

    View view;
    BottomNavigationView navigation;
    NavigationView navigationView;
    private FragmentActivity fragmentActivity;
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
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
        view = inflater.inflate(R.layout.activity_second_page, container, false);

        findView();
        setToolBar();
        ViewPager viewPager =view.findViewById(R.id.viewpager);

        ViewPagerNewsAdapt viewPagerNewsAdapt = new ViewPagerNewsAdapt(getChildFragmentManager());
        viewPager.setAdapter(viewPagerNewsAdapt);
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "همه");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "خبر");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "اطلاعیه");
        setNavigation();
        return view;
    }


    private void findView() {
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
                        ServiceFragment fragment2 = new ServiceFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.replace(R.id.drawer, fragment2);
                        fragmentTransaction.commit();
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        HomeFragment fragment = new HomeFragment();
                        FragmentManager fragmentManager1 = getFragmentManager();
                        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                        fragmentTransaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
                        fragmentTransaction1.addToBackStack(null);
                        fragmentTransaction1.replace(R.id.drawer, fragment);
                        fragmentTransaction1.commit();
                }
                return false;
            }
        });
    }
}
