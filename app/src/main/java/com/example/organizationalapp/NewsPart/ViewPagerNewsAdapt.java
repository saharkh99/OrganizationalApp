package com.example.organizationalapp.NewsPart;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.organizationalapp.NewsPart.NewsFragmentList;

import java.util.ArrayList;


public class ViewPagerNewsAdapt extends FragmentPagerAdapter {
    private  final ArrayList<Fragment>fragments=new ArrayList<>();
    private  final ArrayList<String>titles=new ArrayList<>();
    public ViewPagerNewsAdapt(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0: // Fragment # 0 - This will show FirstFragment
//                return NewsFragmentList.newInstance(0, "همه");
//            case 1: // Fragment # 0 - This will show FirstFragment different title
//                return NewsFragmentList.newInstance(1, "اخبار");
//            case 2: // Fragment # 1 - This will show SecondFragment
//                return NewsFragmentList.newInstance(2, "اطلاعیه ها");
//            default:
//                return null;
//        }
        return fragments.get(position);
      //  return NewsFragmentList.newInstance();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    @Override
    public int getCount() {
        return 3;
    }
    public void addFragment(Fragment fragment,String title){

        fragments.add(fragment);
        titles.add(title);
    }
}
