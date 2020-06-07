package com.example.organizationalapp.NewsPart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizationalapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFragmentList extends Fragment  {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.news_part_layout, container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), DataReceive.getNews(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                List<News>list=new ArrayList<>();
                list=DataReceive.getNews(getActivity());
                Toast.makeText(getActivity(), list.get(postion).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("title", list.get(postion).getTitle());
                intent.putExtra("date", list.get(postion).getDate());
                intent.putExtra("des", list.get(postion).getDescription());
                intent.putExtra("tag", list.get(postion).getTag());
                intent.putExtra("img", list.get(postion).getImg());
                startActivity(intent);

            }
        });
        return view;
    }

    public static NewsFragmentList newInstance() {

        Bundle args = new Bundle();
        NewsFragmentList fragment = new NewsFragmentList();
        fragment.setArguments(args);
        return fragment;
    }


}
