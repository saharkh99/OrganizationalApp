package com.example.organizationalapp;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.organizationalapp.NewsPart.NewsFragment;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Navigation{
    FragmentManager fragmentManager;
   //public static boolean newsFlag,serviceFlag=false;
   // public static boolean homeFlag=true;
    static boolean homeClicked,serviceClicked,newsClicked=false;
    public void setNavigation(NavigationView navigationView, final FragmentActivity fragmentActivity) {
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
                        changingNewsFragment(fragmentActivity);

                        break;
                    case R.id.service_part:
                        changingServiceFragment(fragmentActivity);
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        changingHomeFragment(fragmentActivity);
                        break;
                }
                return false;
            }
        });
    }

    public void setButtomNavigation(BottomNavigationView navigation, final FragmentActivity fragmentActivity) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        changingNewsFragment(fragmentActivity);

                        break;
                    case R.id.service_part:
                        changingServiceFragment(fragmentActivity);
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        changingHomeFragment(fragmentActivity);
                        break;
                }
                return false;
            }


        });
    }

    public void changingNewsFragment(FragmentActivity fragmentActivity) {

        if(!this.newsClicked) {
            NewsFragment fragment2 = new NewsFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
            Log.d("log", homeClicked+" "+newsClicked+" "+serviceClicked);

            fragmentTransaction.replace(R.id.drawer, fragment2);
            //fragmentTransaction.addToBackStack("BACK_STACK_ROOT_TAG");
            //  fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            /////////////////////////////////////////////////////////////////////////////////////////////
            fragmentTransaction.commit();
            this.serviceClicked = false;
            this.newsClicked = true;
            this.homeClicked = false;
        }
        }


    public void changingServiceFragment(FragmentActivity fragmentActivity) {


        if(!this.serviceClicked) {
            ServiceFragment fragment2 = new ServiceFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Log.d("log", homeClicked+" "+newsClicked+" "+serviceClicked);

            fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
            fragmentTransaction.replace(R.id.drawer, fragment2);
            //  fragmentTransaction.addToBackStack("BACK_STACK_ROOT_TAG");
            //fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            /////////////////////////////////////////////////////////////////////////////////////////////
            fragmentTransaction.commit();
            //newsFlag = false;
            //homeFlag=false;
            //serviceFlag=true;
            //Log.d("home", String.valueOf(homeFlag)+"service");
            //  }
            this.serviceClicked = true;
            this.newsClicked = false;
            this.homeClicked = false;
        }
    }

    public void changingHomeFragment(FragmentActivity fragmentActivity) {

        if(!this.homeClicked) {
            HomeFragment fragment2 = new HomeFragment();
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Log.d("log", homeClicked+" "+newsClicked+" "+serviceClicked);
            if (newsClicked) {
                fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left, R.animator.enter_from_right, R.animator.exit_to_left);
            }
           else  {
               fragmentTransaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right, R.animator.enter_from_left, R.animator.exit_to_right);
           }
            // fragmentTransaction.addToBackStack("home");
            //  fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            // fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.drawer, fragment2);
            fragmentTransaction.commit();
            this.serviceClicked = false;
            this.newsClicked = false;
            this.homeClicked = true;
        }
    }

}
