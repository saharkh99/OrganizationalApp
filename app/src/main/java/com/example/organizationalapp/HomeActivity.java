package com.example.organizationalapp;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.organizationalapp.ForntPart.BaseContext;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseContext {

    MaterialButton news, service;
    BottomNavigationView navigation;
    NavigationView navigationView;
    Navigation nv;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nv=new Navigation();
        findView();
        setNavigation();
        welcome.setText(User.getName() + "   عزیز" + "\n" + "به برنامه جامع خوش آمدید");
       // welcome.setText(getIntent().getStringExtra("name") + "   عزیز" + "\n" + "به برنامه جامع خوش آمدید");
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv.changingNewsIntent(HomeActivity.this);
                navigation.setSelectedItemId(R.id.news_part);

            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv.changingServiceIntent(HomeActivity.this);
                navigation.setSelectedItemId(R.id.service_part);

            }
        });

        setToolBar();
    }

    private void setNavigation() {
        nv.setNavigation(navigationView, this);
        navigation.setSelectedItemId(R.id.home_part);
        nv.setButtomNavigation(navigation, this);

    }

    private void findView() {
        news = findViewById(R.id.news_btn);
        service = findViewById(R.id.service_btn);
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
        welcome = findViewById(R.id.welcome);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
