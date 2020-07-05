package com.example.organizationalapp.ServicePart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizationalapp.Login.BAseApiService;
import com.example.organizationalapp.Login.UtilsApi;
import com.example.organizationalapp.R;
import com.example.organizationalapp.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFragment extends Fragment {
    static List<Services>nList,nlist1,nlist2,nlist3;
    FragmentActivity fragmentActivity;
    View view;
    static ProgressBar progressBar;
    static RecyclerView recyclerView,recyclerView3,recyclerView2;
    static TextView management,entertainment,general;
    static View line1,line2,line3;
    static String colors[] = {"#ff438a5e", "#ff7bc0a3", "#ffc1f880", "#ff9bdeac", "#ff75a8d3", "#ff3e8057", "#ff99b898", "#ffcbecc9", "#ff6daa92", "#ff578581"};

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_service, container, false);
        findView(view);

        progressBar.setVisibility(View.VISIBLE);
        getService(getActivity());
        return view;
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recycle1);
        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView3 = view.findViewById(R.id.recycle3);
        management=view.findViewById(R.id.management);
        entertainment=view.findViewById(R.id.ent);
        general=view.findViewById(R.id.general);
        line1=view.findViewById(R.id.line1);
        line2=view.findViewById(R.id.line2);
        line3=view.findViewById(R.id.line3);
        progressBar=view.findViewById(R.id.progress_circular);
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    public static List<Services> getService(Activity activity){
        BAseApiService mApiService;
        mApiService = UtilsApi.getAPIService();
        Log.d("token2", Token.getToken());
        mApiService.getService(Token.getToken()).
                enqueue(new Callback<List<Services>>() {
                    @Override
                    public void onResponse(Call< List < Services >> call, Response< List < Services >> response){
                        nList=new ArrayList<>();
                        nlist1=new ArrayList<>();
                        nlist2=new ArrayList<>();
                        nlist3=new ArrayList<>();
                        int j = 0;
                        if (response.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Log.d("service", response.body().toString());
                            nList=response.body();
                            setVisible();

                            Random rnd = new Random();

                            for(int i=0;i<nList.size();i++){
                                if(nList.get(i).getParent().equals("general")) {
                                    nList.get(i).setImg(R.drawable.general);
                                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                    nList.get(i).setColor(Color.parseColor(colors[(j++) % 10]));
                                    Log.d("color", Integer.toString(Color.parseColor("#ffe0dede")));
                                    nlist1.add(nList.get(i));

                                }
                                else if(nList.get(i).getParent().equals("entpart")){
                                    nList.get(i).setImg(R.drawable.entertainment);
                                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                    nList.get(i).setColor(Color.parseColor(colors[(j++) % 10]));
                                    nlist2.add(nList.get(i));

                                }
                                else{
                                    nList.get(i).setImg(R.drawable.special);
                                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                    nList.get(i).setColor(Color.parseColor(colors[(j++) % 10]));
                                    nlist3.add(nList.get(i));

                                }

                            }
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, nlist1);
                            recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                            recyclerView.setAdapter(serviceAdapter);
                            ServiceAdapter serviceAdapter1 = new ServiceAdapter(activity, nlist3);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                            recyclerView2.setAdapter(serviceAdapter1);
                            ServiceAdapter serviceAdapter2 = new ServiceAdapter(activity, nlist2);
                            recyclerView3.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                            recyclerView3.setAdapter(serviceAdapter2);
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

    private static void setVisible() {
        management.setVisibility(View.VISIBLE);
        entertainment.setVisibility(View.VISIBLE);
        general.setVisibility(View.VISIBLE);
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.VISIBLE);
        line3.setVisibility(View.VISIBLE);
    }


}
