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

import android.util.Log;
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
    static TextView welcome;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        //Navigation.homeFlag=true;
        BaseActivity.navigation.setSelectedItemId(R.id.home_part);

        findView();
        welcome.setText(User.getName() + "   عزیز" + "\n" + "به برنامه جامع خوش آمدید");
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.navigation.setSelectedItemId(R.id.news_part);
               // nv.changingNewsFragment(fragmentActivity);

            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.navigation.setSelectedItemId(R.id.service_part);
             //   nv.changingServiceFragment(fragmentActivity);
            }
        });

        return view;
    }

    private void findView() {
        news = view.findViewById(R.id.news_btn);
        service = view.findViewById(R.id.service_btn);
        welcome = view.findViewById(R.id.welcome);
     }

    @Override
    public void onPause() {
        welcome.setVisibility(View.INVISIBLE);
        super.onPause();

    }
}