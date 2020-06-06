package com.example.organizationalapp.ServicePart;

import android.content.Context;
import android.graphics.Color;

import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataRecieveForService {

    public static List<Services> getService(Context context){

        List<Services>nList=new ArrayList<>();
        for(int i=0;i<5;i++) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Services services=new Services();
            services.setImg(R.drawable.logogreensr);
            services.setName("خدمات فلان");
            services.setColor(Color.rgb(r,g,b));
            nList.add(services);
        }
        return nList;

    }
}
