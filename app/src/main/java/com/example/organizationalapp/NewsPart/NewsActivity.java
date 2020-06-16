package com.example.organizationalapp.NewsPart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.organizationalapp.BaseActivity;
import com.example.organizationalapp.ForntPart.BaseContext;
import com.example.organizationalapp.HomeFragment;
import com.example.organizationalapp.Navigation;
import com.example.organizationalapp.R;
import com.example.organizationalapp.ServicePart.ServiceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NewsActivity extends BaseContext {

    TextView titleTV,dateTV,tagTV,desTV;
    ImageView imgTV;
    String title,date,tag,des;
    int img;
    BottomNavigationView navigation;
    NavigationView navigationView;
    Navigation nv;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getData();
        findView();
        inint();
        setToolBar();
        nv=new Navigation();
        setNavigation();
        setButtomNavigation(navigation);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));

    }

    private void inint() {
        titleTV.setText(title);
        desTV.setText(des);
        dateTV.setText(date);
        tagTV.setText(tag);
        imgTV.setImageResource(img);
    }

    private void findView() {
        titleTV=findViewById(R.id.title_news);
        tagTV=findViewById(R.id.tag_news);
        desTV=findViewById(R.id.description_news);
        dateTV=findViewById(R.id.date_news);
        imgTV=findViewById(R.id.img_news);
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.drawer);
    }

    private void getData() {
        Intent intent = getIntent();
        title=intent.getStringExtra("title");
        date=intent.getStringExtra("date");
        tag=intent.getStringExtra("tag");
        des=intent.getStringExtra("des");
        img=intent.getIntExtra("img", 0);
    }
    private void setNavigation() {
        nv.setNavigation(navigationView, this);

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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbars, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbars.setNavigationIcon(R.drawable.menu);
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewsActivity.this, BaseActivity.class);
                intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
                startActivity(intent);
            }
        });
    }
    public void setButtomNavigation(BottomNavigationView navigation) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            FragmentManager fragmentManager=getSupportFragmentManager();
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news_part:
                        if(!Navigation.newsClicked) {
                            item.setChecked(true);
                            Intent intent = new Intent(NewsActivity.this, BaseActivity.class);
                            intent.putExtra("frag", "news");
                            startActivity(intent);
                            return true;
                        }
                        break;
                    case R.id.service_part:
                        if(!Navigation.serviceClicked) {
                            item.setChecked(true);
                            Intent intent = new Intent(NewsActivity.this, BaseActivity.class);
                            intent.putExtra("frag", "service");
                            startActivity(intent);
                        }
                        break;
                    case R.id.home_part:
                        if(!Navigation.homeClicked) {
                            item.setChecked(true);
                            Intent intent = new Intent(NewsActivity.this, BaseActivity.class);
                            intent.putExtra("frag", "home");
                            startActivity(intent);
                        }

                        break;
                }
                return false;
            }
        });
    }

}
