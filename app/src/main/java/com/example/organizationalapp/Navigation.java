package com.example.organizationalapp;

import android.os.Build;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.organizationalapp.NewsPart.NewsFragment;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Navigation {
    public static boolean homeClicked, serviceClicked, newsClicked = false;
    public void setButtomNavigation(BottomNavigationView navigation,final FragmentManager fragmentManager) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                       changingNewsFragment(fragmentManager);
                       item.setChecked(true);

                        break;
                    case R.id.service_part:
                        changingServiceFragment( fragmentManager);
                        item.setChecked(true);
                        break;
                    case R.id.home_part:
                        item.setChecked(true);
                        changingHomeFragment(fragmentManager);


                        break;
                }
                return false;
            }
        });
    }
    public void setNavigation(NavigationView navigationView,FragmentManager fragmentManager,DrawerLayout drawerLayout) {
        View v = navigationView.getHeaderView(0);
        TextView name = v.findViewById(R.id.name);
        TextView intro = v.findViewById(R.id.intro);
        TextView role=v.findViewById(R.id.role);
        String namestr = User.getName().trim();
        String s = String.valueOf(namestr.charAt(0));
        name.setText(namestr);
        intro.setText(s);
        role.setText(User.getRole());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        changingNewsFragment(fragmentManager);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        break;
                    case R.id.service_part:
                        changingServiceFragment(fragmentManager);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        break;
                    case R.id.home_part:
                        changingHomeFragment(fragmentManager);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        BaseActivity.navigation.setSelectedItemId(item.getItemId());
                        break;
                }
                return false;
            }
        });
    }
    public void changingNewsFragment(FragmentManager fragmentManager) {
        if(!Navigation.newsClicked) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
            transaction.replace(R.id.fragment_container, NewsFragment.newInstance());
            transaction.commit();
           setNavigationItemSelect(false, false, true);
        }
    }


    public void changingServiceFragment(FragmentManager fragmentManager) {


        if(!Navigation.serviceClicked) {
            FragmentTransaction transaction1 = fragmentManager.beginTransaction();
            transaction1.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            transaction1.replace(R.id.fragment_container, ServiceFragment.newInstance());
            transaction1.commit();
            setNavigationItemSelect(false, true, false);
        }
    }

    public void changingHomeFragment(FragmentManager fragmentManager) {

        if(!Navigation.homeClicked) {
            FragmentTransaction transaction2 = fragmentManager.beginTransaction();
            if (Navigation.newsClicked) {
                transaction2.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_left, R.animator.exit_to_right);
            } else {
                transaction2.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_right, R.animator.exit_to_left);
            }
            transaction2.replace(R.id.fragment_container, HomeFragment.newInstance());
            transaction2.commit();
            setNavigationItemSelect(true, false, false);
        }
    }
    public static void setNavigationItemSelect(boolean homeClicked,boolean serviceClicked,boolean newsClicked){
        Navigation.homeClicked=homeClicked;
        Navigation.serviceClicked=serviceClicked;
        Navigation.newsClicked=newsClicked;
    }

}
