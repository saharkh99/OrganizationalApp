package com.example.organizationalapp;

import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.organizationalapp.NewsPart.NewsFragment;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Navigation {
    public static boolean homeClicked, serviceClicked, newsClicked = false;
    public void setButtomNavigation(BottomNavigationView navigation,final Fragment fragment,final FragmentManager fragmentManager) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                       changingNewsFragment(fragment, fragmentManager);
                       item.setChecked(true);

                        break;
                    case R.id.service_part:
                        changingServiceFragment(fragment, fragmentManager);
                        item.setChecked(true);
                        break;
                    case R.id.home_part:
                        item.setChecked(true);
                        changingHomeFragment(fragment, fragmentManager);


                        break;
                }
                return false;
            }
        });
    }
    public void setNavigation(NavigationView navigationView, final Fragment fragment,FragmentManager fragmentManager,DrawerLayout drawerLayout) {
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
                        changingNewsFragment(fragment,fragmentManager);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.service_part:
                        changingServiceFragment(fragment,fragmentManager);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        changingHomeFragment(fragment,fragmentManager);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        break;
                }
                return false;
            }
        });
    }
    public void changingNewsFragment(Fragment fragment,FragmentManager fragmentManager) {
        if(!Navigation.newsClicked) {

            fragment = new NewsFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
            Navigation.serviceClicked = false;
            Navigation.newsClicked = true;
            Navigation.homeClicked = false;
        }
    }


    public void changingServiceFragment(Fragment fragment,FragmentManager fragmentManager) {


        if(!Navigation.serviceClicked) {
            fragment = new ServiceFragment();
            FragmentTransaction transaction1 = fragmentManager.beginTransaction();
            transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            transaction1.replace(R.id.fragment_container, fragment);
            transaction1.commit();
            Navigation.serviceClicked = true;
            Navigation.newsClicked = false;
            Navigation.homeClicked = false;
        }
    }

    public void changingHomeFragment(Fragment fragment,FragmentManager fragmentManager) {

        if(!Navigation.homeClicked) {
            fragment = new HomeFragment();
            FragmentTransaction transaction2 = fragmentManager.beginTransaction();
            if (Navigation.newsClicked) {
                transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            } else {
                transaction2.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
            }                            transaction2.replace(R.id.fragment_container, fragment);
            transaction2.commit();
            Navigation.serviceClicked = false;
            Navigation.newsClicked = false;
            Navigation.homeClicked = true;
        }
    }

}
