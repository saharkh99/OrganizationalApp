package com.example.organizationalapp.NewsPart;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.organizationalapp.NewsPart.NewsFragmentList;

public class ViewPagerNewsAdapt extends FragmentPagerAdapter {
    public ViewPagerNewsAdapt(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return NewsFragmentList.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
