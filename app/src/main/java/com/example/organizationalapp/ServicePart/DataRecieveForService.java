package com.example.organizationalapp.ServicePart;

import android.content.Context;
import android.graphics.Color;

import com.example.organizationalapp.Login.BAseApiService;
import com.example.organizationalapp.Login.UtilsApi;
import com.example.organizationalapp.NewsPart.News;
import com.example.organizationalapp.R;
import com.example.organizationalapp.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Callback;

import android.util.Log;
import android.view.View;

import com.example.organizationalapp.Login.BAseApiService;
import com.example.organizationalapp.Login.UtilsApi;
import com.example.organizationalapp.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class DataRecieveForService {
    static List<Services>nList;
    public static List<Services> getService(Context context){
        BAseApiService mApiService;
        mApiService =UtilsApi.getAPIService();
        Log.d("token2", Token.getToken());
        mApiService.getService(Token.getToken()).
                enqueue(new Callback<List<Services>>() {
                    @Override
                    public void onResponse(Call < List < Services >> call, Response < List < Services >> response){
                        nList=new ArrayList<>();
                        if (response.isSuccessful()) {
                            Log.d("service", response.body().toString());
                            nList=response.body();
                        }
                    }

                    @Override
                    public void onFailure (Call < List < Services >> call, Throwable t){
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        t.printStackTrace();
                    }
                });


        return nList;

    }
}
