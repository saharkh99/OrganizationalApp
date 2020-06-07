package com.example.organizationalapp.ServicePart;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.NewsPart.DataReceive;
import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.NewsPart.NewsAdapter;
import com.example.organizationalapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    NavigationView navigationView;
    BottomNavigationView navigation;
    Navigation nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        nv=new Navigation();
        findView();
        setToolBar();
        setNavigation();
        RecyclerView recyclerView = findViewById(R.id.recycle1);
        ServiceAdapter serviceAdapter = new ServiceAdapter(this, DataRecieveForService.getService(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(serviceAdapter);
        List<Services> list=new ArrayList<>();
        list=DataRecieveForService.getService(this);
        //.....................................................................................................
        RecyclerView recyclerView2 = findViewById(R.id.recycle2);
        ServiceAdapter serviceAdapter2 = new ServiceAdapter(this, DataRecieveForService.getService(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView2.setAdapter(serviceAdapter2);
        //.....................................................................................................
        RecyclerView recyclerView3 = findViewById(R.id.recycle3);
        ServiceAdapter serviceAdapter3 = new ServiceAdapter(this, DataRecieveForService.getService(this));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView3.setAdapter(serviceAdapter3);

    }
    private void setNavigation() {
        nv.setNavigation(navigationView, this);
        navigation.setSelectedItemId(R.id.service_part);
        nv.setButtomNavigation(navigation, this);

    }
    public void findView(){
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
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
        toolbars.setNavigationIcon(R.drawable.menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
}
