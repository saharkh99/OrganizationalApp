package com.example.organizationalapp.ServicePart;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.organizationalapp.R;
import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment {
    FragmentActivity fragmentActivity;
    View view;

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_service, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle1);
        ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(serviceAdapter);
        List<Services> list = new ArrayList<>();
        list = DataRecieveForService.getService(getActivity());
        //.....................................................................................................
        RecyclerView recyclerView2 = view.findViewById(R.id.recycle2);
        ServiceAdapter serviceAdapter2 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView2.setAdapter(serviceAdapter2);
        //.....................................................................................................
        RecyclerView recyclerView3 = view.findViewById(R.id.recycle3);
        ServiceAdapter serviceAdapter3 = new ServiceAdapter(getActivity(), DataRecieveForService.getService(getActivity()));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView3.setAdapter(serviceAdapter3);
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
}
