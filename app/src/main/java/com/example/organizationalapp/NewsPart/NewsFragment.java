package com.example.organizationalapp.NewsPart;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NewsFragment extends Fragment {

    View view;
    BottomNavigationView navigation;
    NavigationView navigationView;
    Navigation nv;
    TextView title;
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
        nv=new Navigation();
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
        title=view.findViewById(R.id.up_title);


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
        navigation.setSelectedItemId(R.id.news_part);
        nv.setNavigation(navigationView, fragmentActivity);
        nv.setButtomNavigation(navigation, fragmentActivity);
    }
    @Override
    public void onPause() {
        title.setVisibility(View.INVISIBLE);
        super.onPause();

    }

}
