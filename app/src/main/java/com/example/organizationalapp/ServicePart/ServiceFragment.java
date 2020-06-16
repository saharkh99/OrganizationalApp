package com.example.organizationalapp.ServicePart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {
    NavigationView navigationView;
    BottomNavigationView navigation;
    Navigation nv;
    DrawerLayout drawerLayout;
    FragmentActivity fragmentActivity;
    boolean booltrans;
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
        nv=new Navigation();
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
        drawerLayout=view.findViewById(R.id.drawer);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        fragmentActivity=(FragmentActivity) activity;
        super.onAttach(activity);
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
        navigation.setSelectedItemId(R.id.service_part);
        nv.setNavigation(navigationView, fragmentActivity);
        nv.setButtomNavigation(navigation, fragmentActivity);

    }

    @Override
    public void onResume() {
//        Log.d("home", "resume"+Navigation.homeFlag+"  news"+Navigation.newsFlag+" ser"+Navigation.serviceFlag);
//        Navigation.homeFlag=false;
//        Navigation.newsFlag=false;
//        Navigation.serviceFlag=true;
        super.onResume();
    }
}
