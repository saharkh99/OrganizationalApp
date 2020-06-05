package com.example.organizationalapp.ServicePart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.organizationalapp.NewsPart.DataReceive;
import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.NewsPart.NewsAdapter;
import com.example.organizationalapp.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
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
}
