package com.example.organizationalapp.ServicePart;

import android.content.Context;

import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.R;

import java.util.ArrayList;
import java.util.List;

public class DataRecieveForService {
    public static List<Services> getService(Context context){
        List<Services>nList=new ArrayList<>();
        for(int i=0;i<5;i++) {
            Services services=new Services();
            services.setImg(R.drawable.logogreensr);
            services.setName("خدمات فلان");
            nList.add(services);
        }
        return nList;

    }
}
