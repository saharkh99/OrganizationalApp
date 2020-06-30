package com.example.organizationalapp.ServicePart;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFragment extends Fragment {
    static List<Services>nList;
    FragmentActivity fragmentActivity;
    View view;
    static RecyclerView recyclerView,recyclerView3,recyclerView2;

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_service, container, false);
        recyclerView = view.findViewById(R.id.recycle1);
        recyclerView2 = view.findViewById(R.id.recycle2);
        recyclerView3 = view.findViewById(R.id.recycle3);

//        ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
//        recyclerView.setAdapter(serviceAdapter);
//        //.....................................................................................................
//        RecyclerView recyclerView2 = view.findViewById(R.id.recycle2);
//        ServiceAdapter serviceAdapter2 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
//        recyclerView2.setAdapter(serviceAdapter2);
//        //.....................................................................................................
//        RecyclerView recyclerView3 = view.findViewById(R.id.recycle3);
//        ServiceAdapter serviceAdapter3 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
//        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
//        recyclerView3.setAdapter(serviceAdapter3);
        getService(getActivity());
        return view;
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
                        if (response.isSuccessful()) {
                            Log.d("service", response.body().toString());
                            nList=response.body();
                            ServiceAdapter serviceAdapter = new ServiceAdapter(activity, nList);
                            recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                            recyclerView.setAdapter(serviceAdapter);
                            ServiceAdapter serviceAdapter1 = new ServiceAdapter(activity, nList);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
                            recyclerView2.setAdapter(serviceAdapter1);
                            ServiceAdapter serviceAdapter2 = new ServiceAdapter(activity, nList);
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



}
