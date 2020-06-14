package com.example.organizationalapp;

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
    boolean flag=false;

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
        NewsFragment fragment2 = new NewsFragment();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /////////////////////////////////////////////////////////////////////////////////////////////

        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        fragmentTransaction.replace(R.id.drawer, fragment2);
        fragmentTransaction.addToBackStack("BACK_STACK_ROOT_TAG");
        fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        /////////////////////////////////////////////////////////////////////////////////////////////
        fragmentTransaction.commit();

    }

    public void changingServiceFragment(FragmentActivity fragmentActivity) {

        ServiceFragment fragment2 = new ServiceFragment();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /////////////////////////////////////////////////////////////////////////////////////////////

        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        fragmentTransaction.replace(R.id.drawer, fragment2);
        fragmentTransaction.addToBackStack("BACK_STACK_ROOT_TAG");
        fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        /////////////////////////////////////////////////////////////////////////////////////////////
        fragmentTransaction.commit();


    }

    public void changingHomeFragment(FragmentActivity fragmentActivity) {
        HomeFragment fragment2 = new HomeFragment();
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left);
        // fragmentTransaction.addToBackStack("home");
        //fragmentManager.popBackStack("BACK_STACK_ROOT_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.addToBackStack("BACK_STACK_ROOT_TAG");
        fragmentTransaction.replace(R.id.drawer, fragment2);
        fragmentTransaction.commit();
    }

}
