package com.example.organizationalapp;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizationalapp.ServicePart.DataRecieveForService;
import com.example.organizationalapp.ServicePart.ServiceAdapter;
import com.example.organizationalapp.ServicePart.Services;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {
    NavigationView navigationView;
    BottomNavigationView navigation;
    FragmentTransaction fragmentTransaction;
    View view;
    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_service, container, false);
        findView();
        setToolBar();
        setNavigation();
        RecyclerView recyclerView = view.findViewById(R.id.recycle1);
        ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(serviceAdapter);
        List<Services> list=new ArrayList<>();
        list=DataRecieveForService.getService(getActivity());
        //.....................................................................................................
        RecyclerView recyclerView2 = view.findViewById(R.id.recycle2);
        ServiceAdapter serviceAdapter2 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView2.setAdapter(serviceAdapter2);
        //.....................................................................................................
        RecyclerView recyclerView3 = view.findViewById(R.id.recycle3);
        ServiceAdapter serviceAdapter3 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView3.setAdapter(serviceAdapter3);
        return view;
    }

    public void findView(){
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
                    case R.id.news_part:
                        item.setTitle("");
                        NewsFragment fragment2 = new NewsFragment();
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
