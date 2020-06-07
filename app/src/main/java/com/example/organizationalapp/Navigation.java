package com.example.organizationalapp;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.organizationalapp.HomeActivity;
import com.example.organizationalapp.NewsPart.SecondPage;
import com.example.organizationalapp.R;
import com.example.organizationalapp.ServicePart.ServiceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Navigation {
    public void setNavigation(NavigationView navigationView, final Context context) {
        View v = navigationView.getHeaderView(0);
        TextView name = v.findViewById(R.id.name);
        TextView intro = v.findViewById(R.id.intro);
        String namestr = User.getName().trim();
        String s = String.valueOf(namestr.charAt(0));
        name.setText(namestr);
        intro.setText(s);

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

    public void setButtomNavigation(BottomNavigationView navigation, final Context context) {
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

    public void changingNewsIntent(Context context) {
        Intent intent = new Intent(context, SecondPage.class);

        context.startActivity(intent);
    }

    public void changingServiceIntent(Context context) {
        Intent intent = new Intent(context, ServiceActivity.class);
        context.startActivity(intent);
    }

    public void changingHomeIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

}
