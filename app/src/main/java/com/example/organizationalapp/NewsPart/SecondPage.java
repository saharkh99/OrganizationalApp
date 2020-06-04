package com.example.organizationalapp.NewsPart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.organizationalapp.R;

public class SecondPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        ViewPager viewPager=findViewById(R.id.viewpager);
        ViewPagerNewsAdapt viewPagerNewsAdapt=new ViewPagerNewsAdapt(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerNewsAdapt);
    }
}
