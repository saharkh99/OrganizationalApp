package com.example.organizationalapp.NewsPart;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsFragmentList extends Fragment {
    private View view;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    List<News>lists=new ArrayList<>();
    List<News>nList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_part_layout, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        Log.d("lists",  lists.size()+" ");
        new processInBackgRound().execute();
        return view;
    }

    public static NewsFragmentList newInstance() {

        Bundle args = new Bundle();
        NewsFragmentList fragment = new NewsFragmentList();
        fragment.setArguments(args);
        return fragment;
    }
    public  InputStream getInputStream(URL url){
        try {
            return  url.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  class processInBackgRound extends AsyncTask<Integer,Void,List<News>>{
        // ProgressDialog progressDialog=new ProgressDialog(NewsActivity.class);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // progressDialog.setMessage();
            // progressDialog.show();
        }

        @Override
        protected List<News> doInBackground(Integer... integers) {
            try {
                int i=0;
                URL url=new URL("https://www.msc.ir/?part=newsadvance&inc=newsadvance&feed=true&feedtype=rss");
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xmlPullParser=factory.newPullParser();
                xmlPullParser.setInput(getInputStream(url),"UTF_8");
                boolean insideItem=false;
                List<String>titles=new ArrayList<>();
                List<String>description=new ArrayList<>();
                String title="",desc="";
                int eventType=xmlPullParser.getEventType();
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    News news=null;
                    if(eventType==XmlPullParser.START_TAG){
                     //   news=

                        if(xmlPullParser.getName().equalsIgnoreCase("item")){
                            insideItem=true;
                        }
                        else if(xmlPullParser.getName().equalsIgnoreCase("title")){
                            if(insideItem){
                           //     news.setTitle(xmlPullParser.nextText());
                              title=  xmlPullParser.nextText();
                            }
                        }
                        else if(xmlPullParser.getName().equalsIgnoreCase("description")){
                            if(insideItem){
                              //  news.setDescription(xmlPullParser.nextText());
                             desc=xmlPullParser.nextText();
                            }
                        }
                        if(insideItem){

                        }
                    }
                    else  if (eventType==XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                        insideItem=false;
                        news=new News();

                        news.setTag("خبر");
                        news.setImg(R.drawable.scrum);
                        news.setTitle(title);
                        news.setDescription(desc);
                        nList.add(i, news);
                        news.setIdNews(i);
                        i++;
                    }
                    eventType=xmlPullParser.next();

                }
            } catch (MalformedURLException | XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            lists=nList;
            Log.d("lista", lists.size()+"");
            Log.d("lista", nList.size()+"");
            return nList;
        }

        @Override
        protected void onPostExecute(List<News> s) {
            super.onPostExecute(s);
            lists=s;
            Log.d("onpost", lists.size()+"");
            newsAdapter = new NewsAdapter(getActivity(),lists);
            Log.d("list",  lists.size()+" ");
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.setOnItemClickListener(new NewsAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int postion) {
                    List<News> list = new ArrayList<>();
                    list = DataReceive.getNews(getActivity());
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra("title", list.get(postion).getTitle());
                    intent.putExtra("date", list.get(postion).getDate());
                    intent.putExtra("des", list.get(postion).getDescription());
                    intent.putExtra("tag", list.get(postion).getTag());
                    intent.putExtra("img", list.get(postion).getImg());
                    startActivity(intent);

                }
            });
        }

    }
    public List<News> getData(){
        List<News>nList=new ArrayList<>();

        try {
            int i=0;
            URL url=new URL("https://www.msc.ir/?part=newsadvance&inc=newsadvance&feed=true&feedtype=rss");
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(getInputStream(url),"UTF_8");
            boolean insideItem=false;
            int eventType=xmlPullParser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                if(eventType==XmlPullParser.START_TAG){
                    News news=null;

                    if(xmlPullParser.getName().equalsIgnoreCase("item")){
                        insideItem=true;
                        news=new News();
                        news.setTag("خبر");
                        news.setImg(R.drawable.scrum);
                        news.setIdNews(i++);
                    }
                    else if(xmlPullParser.getName().equalsIgnoreCase("title")){
                        if(insideItem){
                            news.setTitle(xmlPullParser.nextText());
                        }
                    }
                    else if(xmlPullParser.getName().equalsIgnoreCase("description")){
                        if(insideItem){
                            news.setDescription(xmlPullParser.nextText());
                        }
                    }
                    else if(xmlPullParser.getName().equalsIgnoreCase("description")){
                        if(insideItem){
                            news.setDescription(xmlPullParser.nextText());
                        }
                    }
                    else if(xmlPullParser.getName().equalsIgnoreCase("description")){
                        if(insideItem){
                            news.setDescription(xmlPullParser.nextText());
                        }
                    }
                }
                else  if (eventType==XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }
                eventType=xmlPullParser.next();
            }
        } catch (MalformedURLException | XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nList;
        //return nList;

    }
}
