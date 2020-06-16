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
      //  Navigation.homeFlag=false;
        if(getIntent().getFlags()==Intent.FLAG_ACTIVITY_NEW_TASK){
         //   if(Navigation.homeFlag!=true) {
          //      Navigation.homeFlag=true;
                HomeFragment homeFragment = HomeFragment.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
               // fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left,R.animator.enter_from_right, R.animator.exit_to_left);
                // transaction.addToBackStack(String.valueOf(homeFragment));
                transaction.add(R.id.fragment_container, homeFragment, "HomeFragment").commit();

         //   }

        }

        else {
            NewsFragment newsFragment = NewsFragment.newInstance();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
           // fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            transaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
           // Navigation.homeFlag=true;
          //  Navigation.serviceFlag=false;
          //  Navigation.newsFlag=false;
            transaction.add(R.id.fragment_container, newsFragment,"news").commit();
        }
    }

}
