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
import com.example.organizationalapp.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NewsActivity extends BaseContext {

    TextView titleTV, dateTV, tagTV, desTV;
    ImageView imgTV;
    String title, date, tag, des;
    int img;
    BottomNavigationView navigation;
    NavigationView navigationView;
    Navigation nv;
    public static int code = 0;
    DrawerLayout drawerLayout;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getData();
        findView();
        inint();
        setToolBar();
        nv = new Navigation();
        setButtomNavigation(navigation);
        setNavigation(navigationView);
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
        titleTV = findViewById(R.id.title_news);
        tagTV = findViewById(R.id.tag_news);
        desTV = findViewById(R.id.description_news);
        dateTV = findViewById(R.id.date_news);
        imgTV = findViewById(R.id.img_news);
        navigation = findViewById(R.id.navigation);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
    }

    private void getData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        tag = intent.getStringExtra("tag");
        des = intent.getStringExtra("des");
        img = intent.getIntExtra("img", 0);
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
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = 1;
                intent = new Intent(NewsActivity.this, BaseActivity.class);
                intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
                intent.putExtra("fra", "x");
                startActivity(intent);
            }
        });
    }

    public void setButtomNavigation(BottomNavigationView navigation) {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                intent = new Intent(NewsActivity.this, BaseActivity.class);
                overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left);
                switch (item.getItemId()) {
                    case R.id.news_part:
                        if (!Navigation.newsClicked) {
                            item.setChecked(true);
                            code = 2;
                        }
                        break;
                    case R.id.service_part:
                        if (!Navigation.serviceClicked) {
                            item.setChecked(true);
                            code = 3;
                        }

                        break;
                    case R.id.home_part:
                        if (!Navigation.homeClicked) {
                            item.setChecked(true);
                            code = 4;
                        }

                        break;
                }
                startActivity(intent);
                return false;
            }
        });
    }

    public void setNavigation(NavigationView navigationView) {
        View v = navigationView.getHeaderView(0);
        TextView name = v.findViewById(R.id.name);
        TextView intro = v.findViewById(R.id.intro);
        TextView role = v.findViewById(R.id.role);
        String namestr = User.getName().trim();
        String s = String.valueOf(namestr.charAt(0));
        name.setText(namestr);
        intro.setText(s);
        role.setText(User.getRole());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                intent = new Intent(NewsActivity.this, BaseActivity.class);
                switch (item.getItemId()) {
                    case R.id.news_part:
                        if (!Navigation.newsClicked) {
                            item.setChecked(true);
                            code = 2;
                        }
                        break;
                    case R.id.service_part:
                        if (!Navigation.serviceClicked) {
                            item.setChecked(true);
                            code = 3;
                        }
                        break;
                    case R.id.home_part:
                        item.setTitle("");
                        if (!Navigation.homeClicked) {
                            item.setChecked(true);
                            code = 4;
                        }
                        break;
                }
                startActivity(intent);
                return false;
            }
        });
    }
}
