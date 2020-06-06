package com.example.organizationalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.organizationalapp.ForntPart.BaseContext;
import com.example.organizationalapp.NewsPart.SecondPage;
import com.example.organizationalapp.ServicePart.ServiceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseContext {

    MaterialButton news, service;
    BottomNavigationView navigation;
    NavigationView navigationView;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findView();
        setNavigation();

        welcome.setText(getIntent().getStringExtra("name")+"   عزیز"+ "\n"+"به برنامه جامع خوش آمدید");
        Log.d("name", getIntent().getStringExtra("name"));
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingNewsIntent();
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changingServiceIntent();
            }
        });

        setToolBar();
    }

    private void setNavigation() {
        Navigation.setNavigation(navigationView, this);
        navigation.setSelectedItemId(R.id.home_part);
        Navigation.setButtomNavigation(navigation, this);

    }

    private void findView() {
        news = findViewById(R.id.news_btn);
        service = findViewById(R.id.service_btn);
        navigation = findViewById(R.id.navigation);
        navigationView=findViewById(R.id.navigation_view);
        welcome = findViewById(R.id.welcome);
    }

    public void changingNewsIntent() {
        Intent intent = new Intent(HomeActivity.this, SecondPage.class);

        startActivity(intent);
    }

    public void changingServiceIntent() {
        Intent intent = new Intent(HomeActivity.this, ServiceActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void setToolBar() {
        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setLogo(R.drawable.homewhite);
        toolbars.setNavigationIcon(R.drawable.menu);
        getSupportActionBar().setIcon(R.drawable.logofitwhite);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
