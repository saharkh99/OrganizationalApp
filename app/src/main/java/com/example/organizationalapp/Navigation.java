package com.example.organizationalapp;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.organizationalapp.HomeActivity;
import com.example.organizationalapp.NewsPart.SecondPage;
import com.example.organizationalapp.R;
import com.example.organizationalapp.ServicePart.ServiceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Navigation {
    public static void setNavigation(NavigationView navigationView, final Context context) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        item.setTitle("");
                        changingNewsIntent(context);
                        break;
                    case R.id.service_part:
                        item.setTitle("");
                        changingServiceIntent(context);
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        changingHomeIntent(context);
                        break;
                }
                return false;
            }
        });
    }

    public static void setButtomNavigation(BottomNavigationView navigation, final Context context) {
        navigation.setSelectedItemId(R.id.home_part);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        item.setTitle("");
                        changingNewsIntent(context);
                        break;
                    case R.id.service_part:
                        item.setTitle("");
                        changingServiceIntent(context);
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        changingHomeIntent(context);
                        break;
                }
                return false;
            }
        });
    }

    public static void changingNewsIntent(Context context) {
        Intent intent = new Intent(context, SecondPage.class);

        context.startActivity(intent);
    }

    public static void changingServiceIntent(Context context) {
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }

    public static void changingHomeIntent(Context context) {
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }

}
