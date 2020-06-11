package com.example.organizationalapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.organizationalapp.NewsPart.NewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class BaseActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        frameLayout=findViewById(R.id.fragment_container);
        //String code=intent.getStringExtra("code");
        //Log.d("code", code);
        if(getIntent().getFlags()==Intent.FLAG_ACTIVITY_NEW_TASK){
            HomeFragment homeFragment = HomeFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, homeFragment, "HomeFragment").commit();


        }
        else {
            NewsFragment newsFragment = NewsFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_container, newsFragment,"news").commit();
        }
    }

}
