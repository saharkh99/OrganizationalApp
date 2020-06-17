package com.example.organizationalapp.NewsPart;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.example.organizationalapp.BaseActivity;
import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class NewsFragment extends Fragment {

    View view;
    TabLayout tabLayout;
    private FragmentActivity fragmentActivity;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_second_page, container, false);
        findView();
        RtlViewPager viewPager = view.findViewById(R.id.viewpager);

        ViewPagerNewsAdapt viewPagerNewsAdapt = new ViewPagerNewsAdapt(getChildFragmentManager());
        viewPager.setAdapter(viewPagerNewsAdapt);
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "همه");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "خبر");
        viewPagerNewsAdapt.addFragment(NewsFragmentList.newInstance(), "اطلاعیه");
        return view;
    }

    private void findView() {
        tabLayout = view.findViewById(R.id.pager_header);


    }



}
