package com.example.organizationalapp.NewsPart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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

public class DataReceive {
    public static List<News> getNews(Context context){
//        List<News>nList=new ArrayList<>();
//        for(int i=0;i<5;i++) {
//            News news=new News();
//            news.setDate("1399,02,29");
//            news.setIdNews(i);
//            news.setTag("خبر");
//            news.setTitle("عنوان خبر یا اطلاعیه");
//            news.setImg(R.drawable.scrum);
//            news.setDescription("اساس پیدایش جمهوری اسلامی ایران بر پایه نظریه ولایت فقیه است. حسینعلی منتظری در کتابی به تشریح (روشن سازی) این نظریه پرداخت. بر پایه این نظریه در زمان غیبت امام دوازدهم، فقیه واجد شرایط به عنوان ولی فقیه انتخاب می\u200Cشود که وظایف امام غایب در زمان غیبت را به عهده دارد. نظریه ولایت مطلقه فقیه در سال\u200Cهای اخیر توسط آیت الله خمینی مطرح شده و نظریات مشابهی نیز در آثار فقیهان پیشین نیز هست");
//            nList.add(news);
//        }

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
     public static InputStream getInputStream(URL url){
         try {
             return  url.openConnection().getInputStream();
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         }
     }
//     public static class processInBackgRound extends AsyncTask<Integer,Void,List<News>>{
//        // ProgressDialog progressDialog=new ProgressDialog(NewsActivity.class);
//         @Override
//         protected void onPreExecute() {
//             super.onPreExecute();
//            // progressDialog.setMessage();
//            // progressDialog.show();
//         }
//
//         @Override
//         protected List<News> doInBackground(Integer... integers) {
//
//         }
//
//         @Override
//         protected void onPostExecute(List s) {
//             super.onPostExecute(s);
//
//           //  progressDialog.dissmiss();
//         }
//     }
}
